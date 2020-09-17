package com.example.phonebook.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfBuilder {

    private Document document;
    private final Font defaultFont;
    private static final String DEFAULT_PATH = "src\\main\\resources\\pdf\\";

    public PdfBuilder() {
        defaultFont = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
    }

    public File getPdfAsFile(String fileName) throws FileNotFoundException {
        closeFileForEdit();
        File file = new File(DEFAULT_PATH + fileName);
        if (!file.exists()) {
            throw new FileNotFoundException("No such file with name: " + fileName
                    + ", in path: " + DEFAULT_PATH);
        }
        return file;
    }

    public void createPdfFile(String fileName) throws IOException, DocumentException {
        document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(DEFAULT_PATH + fileName));
        openFileForEdit();
    }

    public void addTextToPDF(String text) throws DocumentException {
        Paragraph paragraph = new Paragraph(text, defaultFont);
        document.add(paragraph);
    }

    private void openFileForEdit() {
        if (document != null) {
            document.open();
        }
    }

    private void closeFileForEdit() {
        if (document != null && document.isOpen()) {
            document.close();
        }
    }
}
