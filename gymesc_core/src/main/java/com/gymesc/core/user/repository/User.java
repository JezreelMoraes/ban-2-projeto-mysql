package com.gymesc.core.user.repository;

import com.gymesc.core.base.repository.BaseEntity;
import com.gymesc.core.base.enumeration.WorkoutLevelEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "USER")
public class User extends BaseEntity {

    @Column(nullable = false, length = 40, name = "EMAIL")
    private String email;

    @Column(nullable = false, name = "PASSWORD")
    private String password;

    @Column(nullable = false, length = 20, name = "NAME")
    private String name;

    @Column(length = 20, name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(length = 15, name = "FEDERAL_IDENTIFICATION")
    private String federalIdentification;

    @Column(nullable = false, name = "WEIGHT")
    private Double weight;

    @Column(nullable = false, name = "HEIGHT")
    private Integer height;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "WORKOUT_LEVEL")
    private WorkoutLevelEnum workoutLevelEnum;
}
