package com.kla.FlixRating.dao;

import com.kla.FlixRating.model.Flix;

import java.util.List;

public interface FlixDao {

    public void addFlix(Flix f);
    public void updateFlix(Flix f);
    public List<Flix> listFlix();
    public Flix getFlixById(int id);
    public void removeFlix(int id);
}
