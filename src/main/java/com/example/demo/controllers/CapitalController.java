package com.example.demo.controllers;

import com.example.demo.models.Capital;
import com.example.demo.repositories.CapitalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/capital")
public class CapitalController {

    private static final Logger log = LoggerFactory.getLogger(CapitalController.class);

    @Autowired
    private CapitalRepository capitalRepository;

    @GetMapping(path = "/add")
    public @ResponseBody
    String addNewCapital(@RequestParam String name) {

        log.info("A new Capital was added by name " + name);

        Capital n = new Capital();
        n.setName(name);
        capitalRepository.save(n);
        return name + " is Saved";
    }



    @GetMapping("/addByView")
    public String displayAddForm(Model model) {
        Capital cap = new Capital();
        model.addAttribute("capital", cap);
        return "addCapital";
    }




    @PostMapping(path = "/getAllAfterAddByView")
    public String addNewCapitalByView(@RequestParam String name, Model model) {
        Capital c = new Capital(name);
        capitalRepository.save(c);
       //return getAllCapitalsByView(model) ; //gör att senaste stad postas in varje gång man trycker enter - fult
        return "redirect:allByView";
    }



    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Capital> getAllCapitals() {
        log.info("All capitals displayed");
        return capitalRepository.findAll();
    }


    @GetMapping(path = "/allByView")
    public String getAllCapitalsByView(Model model) {
        Iterable<Capital> caps = capitalRepository.findAll();
        model.addAttribute("allCapitals", caps);
        model.addAttribute("PageHeading", "Alla huvudstäder");
        model.addAttribute("TableHeading1", "Id");
        model.addAttribute("TableHeading2", "Huvudstad");
        return "allCapitalsListing";
    }



    @GetMapping(path = "/getById")
    public @ResponseBody
    Capital getCapById(@RequestParam Long id) {
        return capitalRepository.findById(id).get();
    }

    @GetMapping(path = "/allByViewDelete")
    public String getAllCapitalsByViewDelete(Model model) {
        Iterable<Capital> caps = capitalRepository.findAll();
        model.addAttribute("allCapitals", caps);
        model.addAttribute("PageHeading", "Alla huvudstäder");
        model.addAttribute("TableHeading1", "Id");
        model.addAttribute("TableHeading2", "Huvudstad");
        return "allCapitalsListingDelete";
    }

    @GetMapping(path = "/deleteById/{id}")
    public String deleteCap(@PathVariable Long id, Model model) {
        try {
            capitalRepository.deleteById(id);
        }catch(EmptyResultDataAccessException e){
            return errorHasOccurred(model, "Det finns ingen huvudstad med angivet id");
        }
        return getAllCapitalsByViewDelete(model);
        //return "redirect:allByViewDelete";
    }

    @GetMapping(path = "error")
    public String errorHasOccurred(Model model, String error){
        model.addAttribute("error", error);
        return "errorTemplate";
    }



    @GetMapping("/editByView/{id}")
    public String editByView(Model model, @PathVariable Long id) {
        Capital cap = capitalRepository.findById(id).get();
        model.addAttribute("capital", cap);
        return "editCapitalThymeleaf";
    }




    @PostMapping(path = "/update")
    public String editCap(@ModelAttribute Capital cap, Model model) {
        System.out.println("editCap");
        capitalRepository.save(cap);
        return getAllCapitalsByViewDelete(model);
    }


}
