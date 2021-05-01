package com.intern.translator.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("")
@RestController
public class IndexController {

    @GetMapping
    public String Index() {
        return "Backend API is up";
    }
}
