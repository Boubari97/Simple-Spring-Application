package com.example.phonebook.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Controller
public class UploadController {

    @GetMapping("/upload")
    public String openUploadPage() {
        return "uploadForm.ftlh";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {
        try (InputStream inputStream = file.getInputStream();
             OutputStream outputStream = new FileOutputStream("src\\main\\resources\\json\\" + file.getName() + ".json")) {
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);
            outputStream.write(data);
            model.addAttribute("successMessage", "File " + file.getName() + " uploaded successfully!");
            model.addAttribute("color", "green");
        } catch (IOException e) {
            model.addAttribute("color", "red");
        }
        return "uploadForm.ftlh";
    }
}
