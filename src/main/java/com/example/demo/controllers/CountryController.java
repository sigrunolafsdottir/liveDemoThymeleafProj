package com.example.demo.controllers;

import com.example.demo.models.Capital;
import com.example.demo.models.Country;
import com.example.demo.repositories.CapitalRepository;
import com.example.demo.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller                            // This means that this class is a Controlle
@RequestMapping(path = "/country")
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private CapitalRepository capitalRepository;

    @GetMapping(path = "/add")
    public @ResponseBody
    String addNewCountry(@RequestParam String name, @RequestParam Long capital) {

        Country n = new Country();
        n.setName(name);

        Capital cap = capitalRepository.findById(capital).get();
        if (cap != null) {
            n.setCapital(cap);
        }

        countryRepository.save(n);
        return name + " is Saved";
    }


    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @GetMapping(path = "/getById")
    public @ResponseBody
    Country getAllCountries(@RequestParam Long id) {
        return countryRepository.findById(id).get();
    }


}
