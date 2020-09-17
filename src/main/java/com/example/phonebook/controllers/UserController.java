package com.example.phonebook.controllers;

import com.example.phonebook.exceptions.ControllerException;
import com.example.phonebook.exceptions.UserNotFoundException;
import com.example.phonebook.model.User;
import com.example.phonebook.services.UserService;
import com.example.phonebook.utils.PdfBuilder;
import com.itextpdf.text.DocumentException;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private static final String DEFAULT_FILE_NAME = "users.pdf";
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public String getUserList(Model model) {
        List<User> userList = userService.findAllUsers();
        model.addAttribute("users", userList);
        return "usersList.ftlh";
    }

    @GetMapping(value = "/users/{uid}")
    public String getUserByUid(@PathVariable("uid") long uid, Model model, Principal principal) {
        Optional<User> optionalUser = userService.findUserByUid(uid);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            model.addAttribute("uid", user.getUid());
            model.addAttribute("username", user.getUsername());
            model.addAttribute("numbers", user.getPhoneNumbers());
            model.addAttribute("roles", user.getAuthorities());
            model.addAttribute("principal", principal);
            return "userProfilePage.ftlh";
        } else {
            throw new UserNotFoundException();
        }
    }

    @GetMapping(value = "/users/pdf", headers = "Accept=application/pdf")
    public void getUsersInPdfFile(HttpServletResponse response) {
        PdfBuilder pdfBuilder = new PdfBuilder();
        try {
            pdfBuilder.createPdfFile(DEFAULT_FILE_NAME);
            pdfBuilder.addTextToPDF("USERS: \n");

            List<User> userList = userService.findAllUsers();
            for (User user : userList) {
                pdfBuilder.addTextToPDF(user.toString());
            }

            File pdf = pdfBuilder.getPdfAsFile(DEFAULT_FILE_NAME);
            byte[] bytes = FileUtil.readAsByteArray(pdf);

            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "inline; filename=users.pdf");
            response.setContentLength(bytes.length);

            try (OutputStream outputStream = response.getOutputStream()) {
                outputStream.write(bytes);
                outputStream.flush();
            }
        } catch (DocumentException | IOException e) {
            throw new ControllerException(e.getMessage());
        }
    }

    @GetMapping(value = "/users/json")
    @ResponseBody
    public List<User> getJsonUsers() {
        return userService.findAllUsers();
    }

    @GetMapping(value = "/profile")
    public String showUserProfile(Principal principal, Model model) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        model.addAttribute("uid", user.getUid());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("numbers", user.getPhoneNumbers());
        model.addAttribute("roles", user.getAuthorities());
        model.addAttribute("principal", principal);
        return "userProfilePage.ftlh";
    }

}
