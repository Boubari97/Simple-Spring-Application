package com.example.phonebook.controllers;

import com.example.phonebook.services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

    private final UploadService uploadService;

    @Autowired
    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @GetMapping("/upload")
    public String openUploadPage() {
        return "uploadForm.ftlh";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {
        if (uploadService.upload(file)) {
            model.addAttribute("successMessage", "File uploaded successfully!");
            model.addAttribute("color", "green");
        } else {
            model.addAttribute("successMessage", "File not uploaded!");
            model.addAttribute("color", "red");
        }
        return "uploadForm.ftlh";
    }
}
