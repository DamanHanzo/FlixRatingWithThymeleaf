package com.kla.FlixRating.service;

import com.kla.FlixRating.model.Flix;
import com.kla.FlixRating.repository.FlixRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FlixServiceImpl implements FlixService {

    @Autowired
    private FlixRepository flixRepository;

    public FlixRepository getFlixRepository() {
        return flixRepository;
    }

    @Override
    @Transactional
    public void addFlix(Flix f){
        this.flixRepository.saveAndFlush(f);
    }

    @Override
    @Transactional
    public void updateFlix(Long id, Flix flix){
        Flix existingFlix = this.flixRepository.findOne(id);
        BeanUtils.copyProperties(flix, existingFlix);
    }

    @Override
    @Transactional
    public List<Flix> listFlix(){
        return this.flixRepository.findAll();
    }

    @Override
    @Transactional
    public Flix getFlixById(Long id){
        return this.flixRepository.getOne(id);
    }

    @Override
    @Transactional
    public void removeFlix(Long id){
        Flix flix = this.flixRepository.findOne(id);
        this.flixRepository.delete(flix);
    }

    @Override
    @Transactional
    public List<Flix> find(String name){
        List<Flix> flixList = this.flixRepository.find(name);
        return flixList;
    }
}