package com.example.phonebook.services;

import com.example.phonebook.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class JsonParser {

    private static final String JSON_PATH = "src/resources/json/";

    public JsonParser() {

    }

    public List<User> parseUserFromFile(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(file, List.class);
    }

    public String writeUsersToJsonFile(List<User> users) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String filePath = JSON_PATH + users.size() + "users.json";
        File jsonFile = new File(filePath);
        if (jsonFile.exists()) {
            try {
                Files.delete(Paths.get(filePath));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        objectMapper.writeValue(jsonFile, users);
        return filePath;
    }

    public List<User> parseUsersFromUri(URL url) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(url, List.class);
    }
}
