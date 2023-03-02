package com.ey.bankingservicesapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HomeController {

    @GetMapping()
    public String findById() {
        return "Home Controller";
    }

    @GetMapping("/greet")
    public String findBooks() {
        return "";
    }

}
