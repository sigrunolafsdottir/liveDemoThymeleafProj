package com.example.demo.repositories;

import com.example.demo.models.Country;
import org.springframework.data.repository.CrudRepository;


public interface CountryRepository extends CrudRepository<Country, Long> {

}


