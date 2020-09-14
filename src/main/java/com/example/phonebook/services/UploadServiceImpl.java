package com.example.phonebook.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Service
public class UploadServiceImpl implements UploadService {

    private static final String DEFAULT_JSON_PATH = "src\\main\\resources\\json\\";
    private static final String DEFAULT_JSON_FORMAT = ".json";

    @Override
    public boolean upload(MultipartFile file) {
        String filePath = DEFAULT_JSON_PATH + file.getName() + DEFAULT_JSON_FORMAT;
        try (InputStream inputStream = file.getInputStream();
             OutputStream outputStream = new FileOutputStream(filePath)) {
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);
            outputStream.write(data);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
