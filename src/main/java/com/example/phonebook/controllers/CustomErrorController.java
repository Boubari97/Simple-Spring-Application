package com.example.phonebook.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping(value = "/access_error")
    public String handleError(@RequestAttribute(value = "exception") AccessDeniedException e,
                              Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        model.addAttribute("errorDetails", e.getCause());
        return "errorPage";
    }

    @Override
    public String getErrorPath() {
        return "/access_error";
    }
}
