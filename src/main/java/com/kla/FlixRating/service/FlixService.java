package com.kla.FlixRating.service;

import com.kla.FlixRating.model.Flix;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FlixService {
    public void addFlix(Flix f);
    public void updateFlix(Long id,Flix f);
    public List<Flix> listFlix();
    public Flix getFlixById(Long id);
    public void removeFlix(Long id);
    public List<Flix> findByName(String name);
    public void updateFlix(Flix f);
    public Page<Flix> listAllByPages(Pageable page);
}
