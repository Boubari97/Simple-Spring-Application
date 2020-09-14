package com.example.phonebook.services;

import com.example.phonebook.model.PhoneNumber;
import com.example.phonebook.repositories.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {

    private final PhoneNumberRepository phoneNumberRepository;

    @Autowired
    public PhoneNumberServiceImpl(PhoneNumberRepository phoneNumberRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
    }

    @Override
    public void saveNumber(PhoneNumber number) {
        phoneNumberRepository.save(number);
    }

    @Override
    public void deleteNumberByUid(long uid) {
        phoneNumberRepository.deleteById(uid);
    }

    @Override
    public Optional<PhoneNumber> findNumberByUid(long uid) {
        return phoneNumberRepository.findById(uid);
    }
}
