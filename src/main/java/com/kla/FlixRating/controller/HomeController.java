package com.kla.FlixRating.controller;

import com.kla.FlixRating.model.Comment;
import com.kla.FlixRating.model.Flix;
import com.kla.FlixRating.model.PageWrapper;
import com.kla.FlixRating.repository.CommentRepository;
import com.kla.FlixRating.service.FlixService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private FlixService flixService;

    @Autowired
    private CommentRepository commentRepository;

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
        flix.setRaters(1L);
        this.flixService.addFlix(flix);
        return "redirect:/flixs";
    }

    @RequestMapping(value="/flix", method= RequestMethod.GET)
    public String addFlix(Model model){
        model.addAttribute("flix", new Flix());
        return "flix/flix-add";
    }

    @RequestMapping(value="flix/search/{name}", method = RequestMethod.POST)
    public String search( @RequestParam("name") String name, Model model, Pageable pageable){
        if(name.length() > 0){
            model.addAttribute("listFlix", this.flixService.findByName(name));

            return "flix/flix-search";
        }
        return  "redirect:/flixs";
    }

    @RequestMapping(value="/flixs", method= RequestMethod.GET)
    public String listFlix(Model model, Pageable pageable){
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
    public String addComment(@PathVariable Long id, Comment comment, Model model){
        Flix existingFlix = this.flixService.getFlixById(id);
        Long raters = existingFlix.getRaters();
        raters += 1;
        existingFlix.setRaters(raters);
        Comment newComment = new Comment(comment.getUsername(), comment.getMessage(), comment.getRating());
        newComment.setFlix(existingFlix);
        this.commentRepository.save(newComment);
        List<Comment> comments = existingFlix.getComments();
        Long ratingSum = 0L;
        for(Comment comment1: comments){
            Long rating = comment.getRating();
            ratingSum += rating;
        }
        float newAvgRating = ratingSum/raters;
        existingFlix.setAvgRating(newAvgRating);
        this.flixService.updateFlix(id, existingFlix);
        model.addAttribute("flix", existingFlix);
        model.addAttribute("comment",new Comment());
        return "flix/flix-view";
    }

}
