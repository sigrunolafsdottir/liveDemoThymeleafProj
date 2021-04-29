package com.example.demo.controllers;

import com.example.demo.models.Present;
import com.example.demo.repositories.PresentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController                            // This means that this class is a Controller
@RequestMapping(path = "/present")
public class PresentController {

    @Autowired
    private PresentRepository presentRepository;

    @GetMapping(path = "/add")
    public String addNewPresent(@RequestParam String name) {

        Present n = new Present();
        n.setName(name);
        presentRepository.save(n);
        return name + " is Saved";
    }

    @GetMapping(path = "/all")
    public Iterable<Present> getAllCapitals() {
        return presentRepository.findAll();
    }


}
