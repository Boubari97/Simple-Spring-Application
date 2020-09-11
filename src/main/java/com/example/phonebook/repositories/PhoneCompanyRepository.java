package com.example.phonebook.repositories;

import com.example.phonebook.model.PhoneCompany;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneCompanyRepository extends CrudRepository<PhoneCompany, Long> {
}
