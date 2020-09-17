package com.example.phonebook.controllers;

import com.example.phonebook.exceptions.RegistrationFailedException;
import com.example.phonebook.model.Role;
import com.example.phonebook.model.User;
import com.example.phonebook.services.RoleService;
import com.example.phonebook.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    private static final int DEFAULT_ROLE_UID = 1;

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public RegistrationController(PasswordEncoder passwordEncoder, UserService userService,
                                  RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String openRegistrationPage() {
        return "registration.ftlh";
    }

    @PostMapping
    public String registration(@RequestParam("username") String username,
                               @RequestParam("password") String password) {
        User user = new User(username, passwordEncoder.encode(password));
        Optional<Role> role = roleService.findRoleByUid(DEFAULT_ROLE_UID);
        if (role.isPresent()) {
            user.addRole(role.get());
            userService.saveUser(user);
            return "redirect:/";
        } else {
            throw new RegistrationFailedException("Can not set role for new user!");
        }
    }
}
