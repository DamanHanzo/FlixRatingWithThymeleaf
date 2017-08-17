package com.kla.FlixRating.repository;

import com.kla.FlixRating.model.Flix;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlixRepository extends JpaRepository<Flix, Long> {
//    @Query("SELECT f FROM Flix f WHERE LOWER(f.name) LIKE %LOWER(:name)%")
    List<Flix> findByNameContaining(String name);
}
