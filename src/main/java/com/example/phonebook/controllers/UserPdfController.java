package com.example.phonebook.controllers;

import com.example.phonebook.model.User;
import com.example.phonebook.repositories.UserRepository;
import com.example.phonebook.services.PdfBuilder;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
public class UserPdfController {

    private static final String FILE_NAME = "users.pdf";
    private final PdfBuilder pdfBuilder;
    private final UserRepository userRepository;

    @Autowired
    public UserPdfController(PdfBuilder pdfBuilder, UserRepository userRepository) {
        this.pdfBuilder = pdfBuilder;
        this.userRepository = userRepository;
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

}
