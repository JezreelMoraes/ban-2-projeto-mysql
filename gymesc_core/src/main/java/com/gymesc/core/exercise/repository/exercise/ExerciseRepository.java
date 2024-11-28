package com.gymesc.core.exercise.repository.exercise;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    @Query("SELECT e FROM Exercise e WHERE e.id = :id")
    Exercise findExerciseById(Long id);

    @Query("SELECT e FROM Exercise e WHERE e.user.id = :userId")
    List<Exercise> listByUserId(Long userId);

    @Query("SELECT e FROM Exercise e WHERE e.user.id = :userId and e.id = :id")
    Exercise findIfOwner(Long userId, Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Exercise e WHERE e.user.id = :userId")
    void deleteAllByUserId(Long userId);
}
