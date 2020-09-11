package com.example.phonebook.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {

    @GetMapping(value = "/")
    public String openStartPage(){
        return "startPage";
    }
}
