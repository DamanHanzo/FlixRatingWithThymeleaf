package com.kla.FlixRating.controller;

import com.kla.FlixRating.model.Comment;
import com.kla.FlixRating.model.Flix;
import com.kla.FlixRating.model.FlixAPI;
import com.kla.FlixRating.model.PageWrapper;
import com.kla.FlixRating.repository.CommentRepository;
import com.kla.FlixRating.service.FlixService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.jws.WebParam;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private FlixService flixService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("message", "Hello World!");
        return "redirect:/flixs";
    }

    @RequestMapping(value="/flix/add", method=RequestMethod.POST)
    public String createFlix(@Valid Flix flix, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "flix/flix-add";
        }
        flix.setAvgRating(0F);
        this.flixService.addFlix(flix);
        return "redirect:/flixs";
    }

    @RequestMapping(value="/flix", method= RequestMethod.GET)
    public String addFlix(Model model){
        model.addAttribute("flix", new Flix());
        return "flix/flix-add";
    }

    @RequestMapping(value="/flixs", method= RequestMethod.GET)
    public String listFlix(Model model,@PageableDefault(sort="avgRating", direction = Sort.Direction.DESC) Pageable pageable){
        Page<Flix> flixes = this.flixService.listAllByPages(pageable);
        PageWrapper<Flix> page = new PageWrapper<Flix>(flixes, "/flixs");
        model.addAttribute("page", page);
        model.addAttribute("listFlix",page.getContent());
        return "flix/flix";
    }

    @RequestMapping(value="/flix/update/{id}", method=RequestMethod.GET)
    public String loadViewPage(@PathVariable Long id, Model model){
        Flix existingFlix = this.flixService.getFlixById(id);
        model.addAttribute("flix", existingFlix);
        model.addAttribute("comment", new Comment());
        return "flix/flix-view";
    }

    @RequestMapping(value="flix/{id}", method = RequestMethod.GET)
    public String updateFlixGET(@PathVariable Long id, Model model){
        model.addAttribute("flix", this.flixService.getFlixById(id));
        return "flix/flix-update";
    }

    @RequestMapping(value="flix/{id}", method = RequestMethod.POST)
    public String updateFlixPOST(@PathVariable Long id, Flix flix){
       this.flixService.updateFlix(id, flix);
        return "redirect:/flixs";
    }

    @RequestMapping(value="flix/remove/{id}", method = RequestMethod.GET)
    public String deleteFlix(@PathVariable Long id){
        this.flixService.removeFlix(id);
        return "redirect:/flixs";
    }

    @RequestMapping(value="flix/comment/{id}", method = RequestMethod.POST)
    public String addComment(@PathVariable Long id, @Valid Comment comment, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
           return "flix/flix-view";
        }
        Flix existingFlix = this.flixService.getFlixById(id);
        Float ratingSum = 0F;
        Long raters = existingFlix.getRaters() == null ? 0L : existingFlix.getRaters();
        List<Comment> comments = existingFlix.getComments();
        for(Comment comment1: comments){
            Float rating = comment1.getRating();
            ratingSum += rating;
        }
        Comment newComment = new Comment(comment.getUsername(), comment.getMessage(), comment.getRating());
        raters += 1L;
        ratingSum += newComment.getRating();
        newComment.setFlix(existingFlix);
        existingFlix.setRaters(raters);
        Float newAvgRating = (ratingSum/raters);
        existingFlix.setAvgRating(newAvgRating);
        this.commentRepository.save(newComment);
        this.flixService.updateFlix(existingFlix);
        return "redirect:/flix/update/"+existingFlix.getId();
    }

    @RequestMapping(value="flix/search", method = RequestMethod.GET)
    public String searchAPI(@RequestParam("name") String name, Model model){
        if(name.length() > 0){
            try {
                FlixAPI flixAPI = this.flixService.searchAPI(name,restTemplateBuilder);
                model.addAttribute("searchList", flixAPI);
            } catch (HttpClientErrorException exception){
                model.addAttribute("searchList", "");
            }
            model.addAttribute("listFlix", this.flixService.findByName(name.toLowerCase()));
            return "flix/flix-searchAPI";
        }
       return "redirect:/flixs";
    }

    @RequestMapping(value = "flix/add/api", method = RequestMethod.POST)
    public String AddFromAPI(Model model){
        Flix flix = new Flix();
        return "redirect:/flixs";
    }

}
