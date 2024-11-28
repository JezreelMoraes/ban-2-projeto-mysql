package com.gymesc.core.workout.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.gymesc.core.workout.repository.dto.WorkoutDTO;
import com.gymesc.core.workout.request.CreateWorkoutRequest;
import com.gymesc.core.workout.request.UpdateWorkoutRequest;
import com.gymesc.core.workout.service.WorkoutService;
import com.gymesc.infrastructure.exception.BusinessException;
import com.gymesc.infrastructure.exception.ForbiddenException;
import com.gymesc.web.security.auth.UserHelper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/secure/workout")
public class SecureWorkoutController {

    private WorkoutService workoutService;

    @PostMapping
    public ResponseEntity<Void> createWorkout(@Valid @RequestBody CreateWorkoutRequest createWorkoutRequest) throws BusinessException {
        this.workoutService.createWorkout(createWorkoutRequest);

        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateWorkout(@Valid @RequestBody UpdateWorkoutRequest updateWorkoutRequest) throws BusinessException {
        this.workoutService.updateWorkout(updateWorkoutRequest);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{workoutId}")
    public ResponseEntity<Void> deleteWorkout(@PathVariable Long workoutId) throws BusinessException {
        this.workoutService.deleteWorkout(workoutId);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{workoutId}")
    public ResponseEntity<WorkoutDTO> findById(@PathVariable Long workoutId) throws BusinessException {
        WorkoutDTO workoutDTO = this.workoutService.findById(workoutId);

        if (workoutDTO != null && (workoutDTO.getUser() == null || !Objects.equals(workoutDTO.getUser().getId(), UserHelper.getUserId()))) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(workoutDTO);
    }

    @GetMapping
    public ResponseEntity<List<WorkoutDTO>> list() throws BusinessException {
        List<WorkoutDTO> workoutDTOList = this.workoutService.listByUser();

        return ResponseEntity.ok(workoutDTOList);
    }

    @Autowired
    public void setWorkoutService(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

}
