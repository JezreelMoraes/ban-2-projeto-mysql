package com.gymesc.core.base.repository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseEntity {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, name = "ID")
    protected Long id;

    @Setter
    @Column(nullable = false, name = "DELETED")
    protected boolean deleted = false;

    @Column(nullable = false, name = "CREATE_DATE", updatable = false)
    protected LocalDateTime createDate;

    @Column(nullable = false, name = "LAST_EDIT")
    protected LocalDateTime lastEdit;

    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDateTime.now();
        this.lastEdit = this.createDate;
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastEdit = LocalDateTime.now();
    }
}
