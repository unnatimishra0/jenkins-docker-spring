package com.nagarro.training.controller;

import com.nagarro.training.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class StatsController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productservice;

    @Autowired
    private ReviewService reviewService;


    @GetMapping("/stats")
    public long[] stats() {

        long[] stats = new long[3];

        stats[0] = userService.countCustomers();
        stats[1] = productservice.countProducts();
        stats[2] = reviewService.approved().size();

        return stats;
    }

}

