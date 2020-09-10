package com.example.phonebook.services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PdfBuilder {

    private Document document;
    private final Font defaultFont;
    private static final String DEFAULT_PATH = "src\\main\\resources\\pdf\\";

    public PdfBuilder(){
        defaultFont = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
    }

    public Document getPdfAsDocument() {
        closeForEdit();
        return document;
    }

    public File getPdfAsFile(String fileName) throws FileNotFoundException {
        closeForEdit();
        File file = new File(DEFAULT_PATH + fileName);
        if (!file.exists()) {
            throw new FileNotFoundException("So such file with name: " + fileName
                    + ", in path: " + DEFAULT_PATH);
        }
        return file;
    }

    public void createPdfFile(String fileName) throws FileNotFoundException, DocumentException {
        document = new Document();
        File file = new File(DEFAULT_PATH + fileName);
        if (file.exists()) {
            try {
                Files.delete(Paths.get(DEFAULT_PATH + fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        PdfWriter.getInstance(document, new FileOutputStream(DEFAULT_PATH + fileName));
        openForEdit();
    }

    public void addTextToPDF(String text) throws DocumentException {
        Paragraph paragraph = new Paragraph(text, defaultFont);
        document.add(paragraph);
    }

    public void openForEdit() {
        document.open();
    }

    public void closeForEdit() {
        document.close();
    }
}
