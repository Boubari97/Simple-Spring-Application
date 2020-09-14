package com.example.phonebook.services;

import com.example.phonebook.model.PhoneCompany;

import java.util.List;
import java.util.Optional;

public interface PhoneCompanyService {

    void saveCompany(PhoneCompany company);

    void deleteCompanyByUid(long uid);

    Optional<PhoneCompany> findCompanyByUid(long uid);

    List<PhoneCompany> findAllCompanies();

}
