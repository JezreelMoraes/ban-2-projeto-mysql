package com.gymesc.core.exercise.repository.exercise;

import com.gymesc.core.base.repository.BaseEntity;
import com.gymesc.core.exercise.repository.category.Category;
import com.gymesc.core.exercise.repository.exercise.enumeration.ExerciseTypeEnum;
import com.gymesc.core.user.repository.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "EXERCISE")
public class Exercise extends BaseEntity {

    @Column(nullable = false, name = "NAME", length = 255)
    private String name;

    @Column(nullable = false, name = "DESCRIPTION", length = 255)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "EXERCISE_TYPE")
    private ExerciseTypeEnum exerciseTypeEnum;

    @ManyToOne()
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false, name = "AUTHOR_ID")
    private User author;
}
