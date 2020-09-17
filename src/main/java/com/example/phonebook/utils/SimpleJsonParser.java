package com.example.phonebook.utils;

import com.example.phonebook.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class SimpleJsonParser {

    private static final String JSON_PATH =
            "classpath:\\src\\main\\resources\\json\\";

    public List<User> parseUserFromFile(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(file, List.class);
    }

    public String writeUsersToJsonFile(List<User> users) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String filePath = JSON_PATH + users.size() + "users.json";
        File jsonFile = new File(filePath);
        objectMapper.writeValue(jsonFile, users);
        return filePath;
    }

    public List<User> parseUsersFromUri(URL url) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(url, List.class);
    }
}
