package com.gymesc.core.workout.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    @Query("SELECT t FROM Workout t WHERE t.id = :id")
    Workout findWorkoutById(Long id);

    @Query("SELECT t FROM Workout t WHERE t.user.id = :userId")
    List<Workout> listByUserId(Long userId);

    @Query("SELECT t FROM Workout t WHERE t.user.id = :userId and t.id = :id")
    Workout findIfOwner(Long userId, Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Workout t WHERE t.user.id = :userId")
    void deleteAllByUserId(Long userId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM WORKOUT_EXERCISE we WHERE we.EXERCISE_ID = :exerciseId", nativeQuery = true)
    void deleteExerciseAssociations(Long exerciseId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM WORKOUT_EXERCISE we WHERE we.WORKOUT_ID = :workoutId", nativeQuery = true)
    void deleteWorkoutAssociations(Long workoutId);
}