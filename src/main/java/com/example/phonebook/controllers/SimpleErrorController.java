package com.example.phonebook.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class SimpleErrorController implements ErrorController{

    private static final String PATH = "/error";

    @RequestMapping(value = "/error")
    public String handleError(@RequestAttribute(value = "errorMessage", required = false) String errorMessage,
                              @RequestAttribute(value = "errorDetails", required = false) String errorDetails,
                              Model model, Exception exception, HttpServletRequest request) {
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("errorDetails", errorDetails);
        model.addAttribute("exceptionCause", exception.getCause());
        model.addAttribute("statusCode", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
        return "errorPage";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
