package com.gymesc.core.exercise.request;

import com.gymesc.core.exercise.repository.exercise.enumeration.ExerciseTypeEnum;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateExerciseRequest {

    @NotBlank @Size(max = 255)
    private String name;

    @NotBlank @Size(max = 255)
    private String description;

    @Valid @NotNull
    private CreateCategoryRequest category;

    @NotNull
    private ExerciseTypeEnum exerciseTypeEnum;

}
