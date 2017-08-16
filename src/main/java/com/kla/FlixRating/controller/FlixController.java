package com.kla.FlixRating.controller;

//import com.kla.FlixRating.model.Flix;
import com.kla.FlixRating.model.Flix;
import com.kla.FlixRating.service.FlixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
//import org.springframework.web.bind.annotation.RequestMethod;

//import java.util.List;


@Controller
public class FlixController {

    @Autowired
    private FlixService flixService;

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
        this.flixService.addFlix(flix);
        return "redirect:/flixs";
    }

    @RequestMapping(value="/flix", method= RequestMethod.GET)
    public String addFlix(Model model){
        model.addAttribute("flix", new Flix());
        return "flix-add";
    }


    @RequestMapping(value="/flixs", method= RequestMethod.GET)
    public String listFlix(Model model){
        model.addAttribute("listFlix",this.flixService.listFlix());
        return "flix";
    }

    @RequestMapping(value="/flix/update/{id}", method=RequestMethod.GET)
    public String loadUpdatePage(@PathVariable Long id, Model model){
        model.addAttribute("flix", this.flixService.getFlixById(id));
        return "flix-update";
    }

    @RequestMapping(value="flix/{id}", method = RequestMethod.POST)
    public String updateFlix(@PathVariable Long id, Flix flix){
        this.flixService.updateFlix(id, flix);
        return "redirect:/flixs";
    }

    @RequestMapping(value="flix/remove/{id}", method = RequestMethod.GET)
    public String deleteFlix(@PathVariable Long id){
        this.flixService.removeFlix(id);
        return "redirect:/flixs";
    }

//    @RequestMapping(value="flix/search", method = RequestMethod.GET)
//    public String loadSearchPage(){
//
//    }

    @RequestMapping(value="flix/search/{name}", method = RequestMethod.POST)
    public String search( @RequestParam("name") String name, Model model){
        List<Flix> flixList = this.flixService.find(name);
        model.addAttribute("listFlix", flixList);
        return "flix";
    }
}
