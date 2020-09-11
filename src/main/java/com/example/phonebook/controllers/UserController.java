package com.example.phonebook.controllers;

import com.example.phonebook.model.User;
import com.example.phonebook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping(value = "/users")
    public String getUserList(Model model) {
        List<User> userList = userRepository.findAll();
        model.addAttribute("users", userList);
        return "usersList.ftlh";
    }

    @GetMapping(value = "/users/{uid}")
    public String getUserByUid(@PathVariable("uid") long uid, Model model, HttpServletRequest request) {
        Optional<User> optionalUser = userRepository.findByUid(uid);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            model.addAttribute("uid", user.getUid());
            model.addAttribute("username", user.getUsername());
            model.addAttribute("numbers", user.getPhoneNumbers());
            return "user.ftlh";
        } else {
            request.setAttribute("errorMessage", "User Not Found");
            request.setAttribute("errorDetails", "Sorry, but user with UID: " + uid + " not found.");
            return "forward:/error";
        }
    }
}
