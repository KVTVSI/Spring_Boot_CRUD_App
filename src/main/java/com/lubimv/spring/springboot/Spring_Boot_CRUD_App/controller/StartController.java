package com.lubimv.spring.springboot.Spring_Boot_CRUD_App.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {
    @GetMapping(value = "/")
    public String startPage() {
        return "users/startPage";
    }
}


