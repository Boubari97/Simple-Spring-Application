package com.example.phonebook.controllers;

import com.example.phonebook.exceptions.ControllerException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ControllerException.class)
    public String exceptionHandle(ControllerException e, HttpServletRequest request, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        model.addAttribute("errorDetails", e.getDetails());
        model.addAttribute("statusCode", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
        return "errorPage";
    }

}
