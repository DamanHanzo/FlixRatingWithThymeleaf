package com.kla.FlixRating.repository;

import com.kla.FlixRating.model.Flix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlixRepository extends JpaRepository<Flix, Long> {
    @Query("SELECT f FROM Flix f WHERE LOWER(f.name) = LOWER(:name)")
    public List<Flix> find(@Param("name") String name);
}
