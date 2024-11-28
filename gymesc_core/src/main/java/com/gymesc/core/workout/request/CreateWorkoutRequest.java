package com.gymesc.core.workout.request;

import java.util.List;

import com.gymesc.core.base.enumeration.WorkoutLevelEnum;
import com.gymesc.core.base.request.DomainIdRequest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateWorkoutRequest {

    @NotBlank @Size(max = 255)
    private String name;

    @NotBlank @Size(max = 255)
    private String description;

    @NotNull
    private Integer duration;

    private WorkoutLevelEnum workoutLevelEnum;

    @Valid @NotNull @Size(min = 1, message = "{validator.message.exerciseList.min.size}")
    private List<DomainIdRequest> exerciseList;

}
