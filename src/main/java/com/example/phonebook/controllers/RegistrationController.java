package com.example.phonebook.controllers;

import com.example.phonebook.model.Role;
import com.example.phonebook.model.User;
import com.example.phonebook.services.RoleService;
import com.example.phonebook.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class RegistrationController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/registration")
    public String openRegistrationPage() {
        return "registration.ftlh";
    }

    @PostMapping(value = "/registration")
    public String registration(@RequestParam("username") String username,
                             @RequestParam("password") String password) {
        User user = new User(username, passwordEncoder.encode(password));
        Optional<Role> role = roleService.findRoleByUid(1);
        role.ifPresent(user::addRole);
        userService.saveUser(user);
        return "redirect:/";
    }
}
