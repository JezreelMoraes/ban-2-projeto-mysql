package com.gymesc.core.exercise.repository.category;

import com.gymesc.core.user.repository.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, name = "ID")
    private Long id;

    @Column(nullable = false, name = "NAME", length = 30)
    private String name;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}
