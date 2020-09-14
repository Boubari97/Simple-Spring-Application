package com.example.phonebook.services;

import com.example.phonebook.model.PhoneNumber;

import java.util.Optional;

public interface PhoneNumberService {

    void saveNumber(PhoneNumber number);

    void deleteNumberByUid(long uid);

    Optional<PhoneNumber> findNumberByUid(long uid);
}
