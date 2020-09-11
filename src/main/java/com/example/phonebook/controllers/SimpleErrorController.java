package com.example.phonebook.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SimpleErrorController implements ErrorController{

    private static final String PATH = "/error";

    @RequestMapping(value = "/error")
    public String showError(@RequestAttribute(value = "errorMessage", required = false) String errorMessage,
                            @RequestAttribute(value = "errorDetails", required = false) String errorDetails,
                            Model model) {
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("errorDetails", errorDetails);
        return "error";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
