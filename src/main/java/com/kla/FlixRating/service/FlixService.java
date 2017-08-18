package com.kla.FlixRating.service;

import com.kla.FlixRating.model.Flix;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FlixService {
    void addFlix(Flix f);
    void updateFlix(Long id,Flix f);
    List<Flix> listFlix();
    Flix getFlixById(Long id);
    void removeFlix(Long id);
    List<Flix> findByName(String name);
    Page<Flix> listAllByPages(Pageable page);
}
