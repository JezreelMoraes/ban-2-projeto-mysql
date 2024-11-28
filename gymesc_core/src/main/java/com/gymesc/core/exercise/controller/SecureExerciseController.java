package com.gymesc.core.exercise.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gymesc.core.exercise.repository.category.dto.CategoryDTO;
import com.gymesc.core.exercise.repository.exercise.dto.ExerciseDTO;
import com.gymesc.core.exercise.request.CreateExerciseRequest;
import com.gymesc.core.exercise.request.UpdateExerciseRequest;
import com.gymesc.core.exercise.service.ExerciseService;
import com.gymesc.infrastructure.exception.BusinessException;
import com.gymesc.web.security.auth.UserHelper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/secure/exercise")
public class SecureExerciseController {

    ExerciseService exerciseService;

    @PostMapping
    public ResponseEntity<Void> createExercise(@Valid @RequestBody CreateExerciseRequest createExerciseRequest) {
        this.exerciseService.createExercise(createExerciseRequest);

        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateExercise(@Valid @RequestBody UpdateExerciseRequest updateExerciseRequest) throws BusinessException {
        this.exerciseService.updateExercise(updateExerciseRequest);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{exerciseId}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long exerciseId) throws BusinessException {
        this.exerciseService.deleteExercise(exerciseId);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{exerciseId}")
    public ResponseEntity<ExerciseDTO> findExerciseById(@PathVariable Long exerciseId) throws BusinessException {
        ExerciseDTO exerciseDTO = this.exerciseService.findExerciseById(exerciseId);

        if (exerciseDTO != null && (exerciseDTO.getUser() == null || !Objects.equals(exerciseDTO.getUser().getId(), UserHelper.getUserId()))) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(exerciseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ExerciseDTO>> list() {
        List<ExerciseDTO> exerciseDTOList = this.exerciseService.listByUser();

        return ResponseEntity.ok(exerciseDTOList);
    }

    @GetMapping(value = "/list-by-workout")
    public ResponseEntity<List<ExerciseDTO>> listByWorkout(@RequestParam Long workoutId) throws BusinessException {
        List<ExerciseDTO> exerciseDTOList = this.exerciseService.listByWorkout(workoutId);

        return ResponseEntity.ok(exerciseDTOList);
    }

    @GetMapping(value = "/list-category-by-user")
    public ResponseEntity<List<CategoryDTO>> listCategoryByUser() {
        List<CategoryDTO> categoryList = this.exerciseService.listCategoryByUser(UserHelper.getUserId());

        return ResponseEntity.ok(categoryList);
    }

    @Autowired
    public void setExerciseService(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }
}
