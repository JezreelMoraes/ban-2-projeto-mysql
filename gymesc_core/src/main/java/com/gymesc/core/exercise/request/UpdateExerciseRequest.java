package com.gymesc.core.exercise.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdateExerciseRequest extends CreateExerciseRequest {

    @NotNull
    private Long id;

}
