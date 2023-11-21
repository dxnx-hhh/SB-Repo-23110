package com.example.selfcoding.api;

import com.example.selfcoding.entity.Coffee;
import com.example.selfcoding.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CoffeeController {
    @Autowired
    private CoffeeRepository coffeeRepository;

    @GetMapping("/api/coffees")
    private List<Coffee> index() {
        return coffeeRepository.findAll();
    }


}
