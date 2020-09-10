package com.example.phonebook.controllers;

import com.example.phonebook.services.PdfBuilder;
import com.itextpdf.text.*;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class UserPdfController {

    private static final String FILE_NAME = "users.pdf";
    private final PdfBuilder pdfBuilder;

    @Autowired
    public UserPdfController(PdfBuilder pdfBuilder) {
        this.pdfBuilder = pdfBuilder;
    }

    @GetMapping(value = "/users"/*, headers = "Accept = application/pdf"*/)
    public void getUsersInPdfFile(HttpServletResponse response) {

        try {
            pdfBuilder.createPdfFile(FILE_NAME);

            // Fill pdf file here

            File pdf = pdfBuilder.getPdfAsFile(FILE_NAME);
            byte[] bytes = FileUtil.readAsByteArray(pdf);

            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "inline; filename=users.pdf");
            response.setContentLength(bytes.length);

            response.getOutputStream().write(bytes);
            response.getOutputStream().flush();
        } catch (IOException | DocumentException e) {
            throw new RuntimeException(e);
        }
    }

}
