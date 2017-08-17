package com.kla.FlixRating.repository;

import com.kla.FlixRating.model.Flix;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface FlixRepository extends PagingAndSortingRepository<Flix, Long> {
//    @Query("SELECT f FROM Flix f WHERE LOWER(f.name) LIKE %LOWER(:name)%")
    List<Flix> findByNameContaining(String name);
    Flix getById(Long id);
}
