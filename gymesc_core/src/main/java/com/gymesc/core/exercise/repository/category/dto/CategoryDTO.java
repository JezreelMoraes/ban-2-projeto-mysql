package com.gymesc.core.exercise.repository.category.dto;

import com.gymesc.core.user.repository.dto.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CategoryDTO {

    private Long id;

    private String name;

    private UserDTO user;

}
