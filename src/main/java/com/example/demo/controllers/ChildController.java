package com.example.demo.controllers;

import com.example.demo.models.Child;
import com.example.demo.models.Country;
import com.example.demo.models.Present;
import com.example.demo.repositories.ChildRepository;
import com.example.demo.repositories.CountryRepository;
import com.example.demo.repositories.PresentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


@RestController
@RequestMapping(path = "/child")
public class ChildController {

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private ChildRepository childRepository;
    @Autowired
    private PresentRepository presentRepository;

    @GetMapping(path = "/add")
    public String addChild(@RequestParam String name, @RequestParam Long country) {

        Child n = new Child();
        n.setName(name);

        Country c = countryRepository.findById(country).get();
        if (c != null) {
            n.setCountry(c);
        }

        childRepository.save(n);
        return name + " is Saved";
    }

    @GetMapping(path = "/addWPresent")
    public String addChildWithPresent(@RequestParam String name, @RequestParam Long country, @RequestParam Long presentId) {

        Child n = new Child();
        n.setName(name);

        Country c = countryRepository.findById(country).get();
        if (c != null) {
            n.setCountry(c);
        }

        Present p = presentRepository.findById(presentId).get();
        if (p != null) {
            n.setWishes(Arrays.asList(p));
        }

        childRepository.save(n);
        return name + " is Saved";
    }


    @GetMapping(path = "/all")
    public Iterable<Child> getAllChildren() {
        // This returns a JSON or XML with the users
        return childRepository.findAll();
    }


}
