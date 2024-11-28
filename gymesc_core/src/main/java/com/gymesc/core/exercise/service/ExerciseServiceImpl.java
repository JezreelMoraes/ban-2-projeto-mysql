package com.gymesc.core.exercise.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gymesc.core.exercise.repository.category.Category;
import com.gymesc.core.exercise.repository.category.CategoryRepository;
import com.gymesc.core.exercise.repository.category.dto.CategoryDTO;
import com.gymesc.core.exercise.repository.exercise.Exercise;
import com.gymesc.core.exercise.repository.exercise.ExerciseRepository;
import com.gymesc.core.exercise.repository.exercise.dto.ExerciseDTO;
import com.gymesc.core.exercise.request.CreateCategoryRequest;
import com.gymesc.core.exercise.request.CreateExerciseRequest;
import com.gymesc.core.exercise.request.UpdateExerciseRequest;
import com.gymesc.core.user.repository.User;
import com.gymesc.core.user.repository.UserRepository;
import com.gymesc.core.workout.repository.Workout;
import com.gymesc.core.workout.repository.WorkoutRepository;
import com.gymesc.infrastructure.exception.BusinessException;
import com.gymesc.infrastructure.exception.ExceptionHelper;
import com.gymesc.web.security.auth.UserHelper;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private WorkoutRepository workoutRepository;
    private ExerciseRepository exerciseRepository;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;

    @Override
    public void createExercise(CreateExerciseRequest createExerciseRequest) {
        User user = this.userRepository.findUserById(UserHelper.getUserId());
        Category category = createOrGetCategory(user, createExerciseRequest.getCategory());

        Exercise exercise = new ModelMapper().map(createExerciseRequest, Exercise.class);
        exercise.setAuthor(user);
        exercise.setUser(user);
        exercise.setCategory(category);

        this.exerciseRepository.saveAndFlush(exercise);
    }

    @Override
    public void updateExercise(UpdateExerciseRequest updateExerciseRequest) throws BusinessException {
        Exercise exercise = this.exerciseRepository.findIfOwner(UserHelper.getUserId(), updateExerciseRequest.getId());

        if (exercise == null) {
            throw ExceptionHelper.exerciseNotFound();
        }

        new ModelMapper().map(updateExerciseRequest, exercise);
        this.exerciseRepository.saveAndFlush(exercise);
    }

    @Override
    public void deleteExercise(Long exerciseId) throws BusinessException {
        Exercise exercise = this.exerciseRepository.findIfOwner(UserHelper.getUserId(), exerciseId);
        if (exercise == null) {
            throw ExceptionHelper.exerciseNotFound();
        }

        this.workoutRepository.deleteExerciseAssociations(exercise.getId());

        this.exerciseRepository.delete(exercise);
    }

    @Override
    public ExerciseDTO findExerciseById(Long exerciseId) throws BusinessException {
        Exercise exercise = this.exerciseRepository.findExerciseById(exerciseId);

        if (exercise == null) {
            throw ExceptionHelper.exerciseNotFound();
        }

        return new ModelMapper().map(exercise, ExerciseDTO.class);
    }

    @Override
    public List<ExerciseDTO> listByUser() {
        List<Exercise> exerciseList = this.exerciseRepository.listByUserId(UserHelper.getUserId());

        return exerciseList.stream().map(exercise -> new ModelMapper().map(exercise, ExerciseDTO.class)).toList();
    }

    @Override
    public List<ExerciseDTO> listByWorkout(Long workoutId) throws BusinessException {
        Workout workout = this.workoutRepository.findIfOwner(UserHelper.getUserId(), workoutId);

        if (workout == null) {
            throw ExceptionHelper.workoutNotFound();
        }

        return workout.getExerciseList().stream().map(exercise -> new ModelMapper().map(exercise, ExerciseDTO.class)).toList();
    }

    @Override
    public List<CategoryDTO> listCategoryByUser(Long userId) {
        List<Category> categoryList = this.categoryRepository.findByUserId(userId);

        return categoryList.stream().map(category -> new ModelMapper().map(category, CategoryDTO.class)).toList();
    }

    @Autowired
    public void setWorkoutRepository(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    @Autowired
    public void setExerciseRepository(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private Category createOrGetCategory(User user, CreateCategoryRequest createCategoryRequest) {
        if (createCategoryRequest.getId() != null) {
            return categoryRepository.findIfOwner(user.getId(), createCategoryRequest.getId());
        }

        Category category = new ModelMapper().map(createCategoryRequest, Category.class);
        category.setUser(user);

        return categoryRepository.save(category);
    }

}
