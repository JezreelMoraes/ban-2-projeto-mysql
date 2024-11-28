package com.gymesc.core.workout.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdateWorkoutRequest extends CreateWorkoutRequest {

    @NotNull
    private Long id;

}
