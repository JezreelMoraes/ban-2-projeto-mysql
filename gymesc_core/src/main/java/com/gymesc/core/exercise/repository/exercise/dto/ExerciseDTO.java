package com.gymesc.core.exercise.repository.exercise.dto;

import com.gymesc.core.exercise.repository.category.dto.CategoryDTO;
import com.gymesc.core.exercise.repository.exercise.enumeration.ExerciseTypeEnum;
import com.gymesc.core.user.repository.dto.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ExerciseDTO {

    private Long id = null;

    private String name;

    private String description;

    private CategoryDTO category;

    private UserDTO user;

    private UserDTO author;

    private ExerciseTypeEnum exerciseTypeEnum;

}
