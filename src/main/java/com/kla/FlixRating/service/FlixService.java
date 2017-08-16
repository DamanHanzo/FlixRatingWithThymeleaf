package com.kla.FlixRating.service;

import com.kla.FlixRating.model.Flix;

import java.util.List;

public interface FlixService {
    public void addFlix(Flix f);
    public void updateFlix(Long id,Flix f);
    public List<Flix> listFlix();
    public Flix getFlixById(Long id);
    public void removeFlix(Long id);
    public List<Flix> find(String name);
}
