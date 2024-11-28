package com.gymesc.core.workout.repository;

import java.util.List;

import com.gymesc.core.base.repository.BaseEntity;
import com.gymesc.core.base.enumeration.WorkoutLevelEnum;
import com.gymesc.core.exercise.repository.exercise.Exercise;
import com.gymesc.core.user.repository.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "WORKOUT")
public class Workout extends BaseEntity {

    @Column(nullable = false, name = "NAME", length = 255)
    private String name;

    @Column(nullable = false, name = "DESCRIPTION", length = 255)
    private String description;

    @Column(nullable = false, name = "BASE_DURATION")
    private Integer duration;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "WORKOUT_LEVEL")
    private WorkoutLevelEnum workoutLevelEnum;

    @Column(name = "SCALING_RULE")
    private String scalingRule;

    @ManyToOne
    @JoinColumn(nullable = false, name = "USER_ID")
    private User user;

    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinTable(name = "WORKOUT_EXERCISE",
            joinColumns = @JoinColumn(name = "WORKOUT_ID"),
            inverseJoinColumns = @JoinColumn(name = "EXERCISE_ID")
    )
    private List<Exercise> exerciseList;
}
