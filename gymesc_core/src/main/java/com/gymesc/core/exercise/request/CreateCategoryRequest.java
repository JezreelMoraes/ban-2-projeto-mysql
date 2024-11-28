package com.gymesc.core.exercise.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateCategoryRequest {

    private Long id;

    @NotBlank
    private String name;

}
