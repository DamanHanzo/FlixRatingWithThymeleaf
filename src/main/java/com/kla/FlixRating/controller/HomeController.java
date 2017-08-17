package com.kla.FlixRating.controller;

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
        return "index";
    }

    @RequestMapping(value="/flix/add", method=RequestMethod.POST)
    public String createFlix(@Valid Flix flix, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "flix-add";
        }
        flix.setRaters(1L);
        Float initRating = (flix.getAvgRating()/5);
        this.flixService.addFlix(flix);
        return "redirect:/flixs";
    }

    @RequestMapping(value="/flix", method= RequestMethod.GET)
    public String addFlix(Model model){
        model.addAttribute("flix", new Flix());
        return "flix-add";
    }

    @RequestMapping(value="/flixs", method= RequestMethod.GET)
    public String listFlix(Model model, Pageable pageable){
        Page<Flix> flixes = this.flixService.listAllByPages(pageable);
        PageWrapper<Flix> page = new PageWrapper<Flix>(flixes, "/flixs");
        model.addAttribute("page", page);
        model.addAttribute("listFlix",page.getContent());
        return "flix";
    }

    @RequestMapping(value="/flix/update/{id}", method=RequestMethod.GET)
    public String loadViewPage(@PathVariable Long id, Model model){
        model.addAttribute("flix", this.flixService.getFlixById(id));
        return "flix-view";
    }

    @RequestMapping(value="flix/{id}", method = RequestMethod.GET)
    public String updateFlixGET(@PathVariable Long id, Model model){
        model.addAttribute("flix", this.flixService.getFlixById(id));
        return "flix-update";
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

    @RequestMapping(value="flix/search/{name}", method = RequestMethod.POST)
    public String search( @RequestParam("name") String name, Model model){
        List<Flix> flixList = this.flixService.findByName(name);
        model.addAttribute("listFlix", flixList);
        return "flix";
    }

    @RequestMapping(value = "flix/addRating")
    public int upRating(@RequestParam("id") Long id){
        Flix existingFlix = this.flixService.getFlixById(id);
        Flix flix = new Flix();
        Long rater = existingFlix.getRaters() == null ? 1L : existingFlix.getRaters()+1L;
        Float avgRating = existingFlix.getAvgRating();
        avgRating = ((avgRating)/rater);
        existingFlix.setRaters(rater);
        existingFlix.setAvgRating(avgRating);
        BeanUtils.copyProperties(existingFlix, flix);
        this.flixService.updateFlix(flix);
        return 3;
    }

    @RequestMapping(value = "flix/subRating/{id}")
    public String downRating(@PathVariable Long id){
        Flix existingFlix = this.flixService.getFlixById(id);
        Flix flix = new Flix();
        Long rater = existingFlix.getRaters() == null ? 1L : existingFlix.getRaters()+1L;
        Float avgRating = existingFlix.getAvgRating();
        avgRating = ((avgRating-10F)/rater);
        existingFlix.setRaters(rater);
        existingFlix.setAvgRating(avgRating);
        BeanUtils.copyProperties(existingFlix, flix);
        this.flixService.updateFlix(flix);
        return "redirect:/flixs";
    }
}
