package com.example.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path="/form")
public class FormController {

    @GetMapping(path="/start")
    public String formStart(){
        return "form";   //htm-dokumentet med ett formulär
    }

    @PostMapping(path="/thanks")
    public String thanks(@RequestParam(name="fname") String fname, @RequestParam(name="lname") String lname, Model model){
        model.addAttribute("fname", fname);
        model.addAttribute("lname", lname);
        return "thanks";
    }


}
