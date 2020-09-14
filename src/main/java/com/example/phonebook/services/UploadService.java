package com.example.phonebook.services;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    boolean upload(MultipartFile file);
}
