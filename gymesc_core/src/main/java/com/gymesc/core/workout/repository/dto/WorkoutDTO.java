package com.gymesc.core.workout.repository.dto;

import java.util.List;

import com.gymesc.core.base.enumeration.WorkoutLevelEnum;
import com.gymesc.core.exercise.repository.exercise.dto.ExerciseDTO;
import com.gymesc.core.user.repository.dto.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class WorkoutDTO {

    private Long id;

    private String name;

    private String description;

    private Integer duration;

    private WorkoutLevelEnum workoutLevelEnum;

    private String scalingRule;

    private UserDTO user;

    private List<ExerciseDTO> exerciseList;

}
