package com.example.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path="/")
public class GreetingController {

    @GetMapping(path="/")
    public String getIndex(){
        System.out.println("i getIndex");
        return "index.html";
    }

    @GetMapping(path="/greeting")
    public String greetingStart(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model){
        model.addAttribute("name", name);
        model.addAttribute("anotherParam", "Hejsan");
        return "greeting";
    }




}
