package com.gymesc.core.exercise.service;

import java.util.List;

import com.gymesc.core.exercise.repository.category.dto.CategoryDTO;
import com.gymesc.core.exercise.repository.exercise.dto.ExerciseDTO;
import com.gymesc.core.exercise.request.CreateExerciseRequest;
import com.gymesc.core.exercise.request.UpdateExerciseRequest;
import com.gymesc.infrastructure.exception.BusinessException;

public interface ExerciseService {

    void createExercise(CreateExerciseRequest createExerciseRequest);

    void updateExercise(UpdateExerciseRequest updateExerciseRequest) throws BusinessException;

    void deleteExercise(Long exerciseId) throws BusinessException;

    ExerciseDTO findExerciseById(Long exerciseId) throws BusinessException;

    List<ExerciseDTO> listByUser();

    List<ExerciseDTO> listByWorkout(Long workoutId) throws BusinessException;

    List<CategoryDTO> listCategoryByUser(Long userId);

}
