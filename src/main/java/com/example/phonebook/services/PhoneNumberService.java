package com.example.phonebook.services;

import com.example.phonebook.model.PhoneNumber;

import java.util.Optional;
import java.util.Set;

public interface PhoneNumberService {

    void saveNumber(PhoneNumber number);

    void deleteNumberByUid(long uid);

    Set<PhoneNumber> findAllByUserUid(long uid);

    Optional<PhoneNumber> findNumberByUid(long uid);
}
