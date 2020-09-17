package com.example.phonebook.controllers;

import com.example.phonebook.exceptions.FileNotUploadedException;
import com.example.phonebook.services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload")
public class UploadController {

    private static final String SUCCESS_MESSAGE_ATTRIBUTE = "successMessage";
    private static final String SUCCESS_MESSAGE = "File uploaded successfully!";
    private final UploadService uploadService;

    @Autowired
    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @GetMapping
    public String openUploadPage() {
        return "uploadForm.ftlh";
    }

    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {
        if (uploadService.upload(file)) {
            model.addAttribute(SUCCESS_MESSAGE_ATTRIBUTE, SUCCESS_MESSAGE);
        } else {
            throw new FileNotUploadedException();
        }
        return "uploadForm.ftlh";
    }
}
