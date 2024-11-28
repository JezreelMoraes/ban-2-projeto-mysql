package com.gymesc.core.exercise.repository.category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.user.id = :userId")
    List<Category> findByUserId(Long userId);

    @Query("SELECT c FROM Category c WHERE c.user.id = :userId and c.id = :id")
    Category findIfOwner(Long userId, Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Category c WHERE c.user.id = :userId")
    void deleteAllByUserId(Long userId);
}
