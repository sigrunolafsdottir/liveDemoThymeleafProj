package com.example.demo.repositories;

import com.example.demo.models.Capital;
import org.springframework.data.repository.CrudRepository;


public interface CapitalRepository extends CrudRepository<Capital, Long> {

}