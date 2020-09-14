package com.example.phonebook.controllers;

import com.example.phonebook.model.User;
import com.example.phonebook.repositories.UserRepository;
import com.example.phonebook.utils.PdfBuilder;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private static final String FILE_NAME = "users.pdf";
    private final PdfBuilder pdfBuilder;
    private final UserRepository userRepository;

    @Autowired
    public UserController(PdfBuilder pdfBuilder, UserRepository userRepository) {
        this.pdfBuilder = pdfBuilder;
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

    @GetMapping(value = "/users/pdf", headers = "Accept=application/pdf")
    public String getUsersInPdfFile(HttpServletResponse response, HttpServletRequest request) {

        try {
            pdfBuilder.createPdfFile(FILE_NAME);
            pdfBuilder.addTextToPDF("USERS: ");

            List<User> userList = userRepository.findAll();
            for (User user : userList) {
                pdfBuilder.addTextToPDF(user.toString());
            }

            File pdf = pdfBuilder.getPdfAsFile(FILE_NAME);
            byte[] bytes = FileUtil.readAsByteArray(pdf);

            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "inline; filename=users.pdf");
            response.setContentLength(bytes.length);

            try (OutputStream outputStream = response.getOutputStream()) {
                outputStream.write(bytes);
                outputStream.flush();
            }
            return "";
        } catch (Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.setAttribute("errorDetails", e.getCause());
            return "forward:/error";
        }
    }

    @GetMapping(value = "/users/json", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> getJsonUsers() {
        return userRepository.findAll();
    }

}
