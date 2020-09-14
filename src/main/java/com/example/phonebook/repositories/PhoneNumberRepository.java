package com.example.phonebook.repositories;

import com.example.phonebook.model.PhoneNumber;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PhoneNumberRepository extends CrudRepository<PhoneNumber, Long> {
    Set<PhoneNumber> findAllByPhoneUserUid(long uid);
}
