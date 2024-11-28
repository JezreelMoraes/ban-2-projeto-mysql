package com.gymesc.core.user.request;

import com.gymesc.core.base.enumeration.WorkoutLevelEnum;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateUserRequest {

    @Email @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotNull @Min(value = 10)
    private Double weight;

    @NotNull @Min(value = 100)
    private Integer height;

    @NotNull
    private WorkoutLevelEnum workoutLevelEnum;

}
