package com.gymesc.core.user.repository.dto;

import com.gymesc.core.base.enumeration.WorkoutLevelEnum;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDTO {

    private Long id;

    private String email;

    private String name;

    private String phoneNumber;

    private String federalIdentification;

    private Double weight;

    private Integer height;

    private WorkoutLevelEnum workoutLevelEnum;

}
