package com.kla.FlixRating.service;

import com.kla.FlixRating.model.Comment;
import com.kla.FlixRating.model.Flix;
import com.kla.FlixRating.repository.CommentRepository;
import com.kla.FlixRating.repository.FlixRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlixServiceImpl implements FlixService {

    @Autowired
    private FlixRepository flixRepository;


    @Autowired
    private CommentRepository commentRepository;
    public FlixRepository getFlixRepository() {
        return flixRepository;
    }

    @Override
    @Transactional
    public void addFlix(Flix f){
        this.flixRepository.save(f);
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
        List<Flix> flixList = new ArrayList<Flix>();
        flixList = (List<Flix>)this.flixRepository.findAll();
        for(Flix flix: flixList){
            List<Comment> comments = new ArrayList<Comment>();
            comments = this.commentRepository.getCommentByFlix_Id(flix.getId());
            flix.setComments(comments);
        }
        return flixList;
    }

    @Override
    @Transactional
    public Flix getFlixById(Long id){
        return this.flixRepository.getById(id);
    }

    @Override
    @Transactional
    public void removeFlix(Long id){
        Flix flix = this.flixRepository.findOne(id);
        this.flixRepository.delete(flix);
    }

    @Override
    @Transactional
    public List<Flix> findByName(String name){
        return this.flixRepository.findByNameStartsWith(name);
    }

    @Override
    @Transactional
    public void updateFlix(Flix f){
        this.flixRepository.save(f);
    }

    @Override
    @Transactional
    public Page<Flix> listAllByPages(Pageable pageable){
        return this.flixRepository.findAll(pageable);
    }
}