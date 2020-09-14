package com.example.phonebook.services;

import com.example.phonebook.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    void saveUser(User user);

    void deleteUserByUid(long uid);

    Optional<User> findUserByUid(long uid);

    List<User> findAllUsers();
}
