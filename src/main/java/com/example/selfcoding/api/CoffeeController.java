package com.example.selfcoding.api;

import com.example.selfcoding.entity.Coffee;
import com.example.selfcoding.repository.CoffeeRepository;
import com.example.selfcoding.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CoffeeController {
//    @Autowired
//    private CoffeeRepository coffeeRepository;
    @Autowired
    private CoffeeService coffeeService;

    @GetMapping("/api/coffees")
    private List<Coffee> index() {
        return coffeeService.index();
    }
//    @GetMapping("/api/coffees")
//    private List<Coffee> index() {
//        return coffeeRepository.findAll();
//    }


}
