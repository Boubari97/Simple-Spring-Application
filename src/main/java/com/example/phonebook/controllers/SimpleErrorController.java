package com.example.phonebook.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SimpleErrorController implements ErrorController{

    private static final String PATH = "/error";

    @RequestMapping(value = "/error")
    public String openError(Model model) {
        model.addAttribute("error", "Something went wrong");
        return "error";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
