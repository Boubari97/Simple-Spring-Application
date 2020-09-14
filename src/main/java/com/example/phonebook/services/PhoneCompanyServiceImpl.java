package com.example.phonebook.services;

import com.example.phonebook.model.PhoneCompany;
import com.example.phonebook.repositories.PhoneCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneCompanyServiceImpl implements PhoneCompanyService {

    private final PhoneCompanyRepository phoneCompanyRepository;

    @Autowired
    public PhoneCompanyServiceImpl(PhoneCompanyRepository phoneCompanyRepository) {
        this.phoneCompanyRepository = phoneCompanyRepository;
    }

    @Override
    public void saveCompany(PhoneCompany company) {
        phoneCompanyRepository.save(company);
    }

    @Override
    public void deleteCompanyByUid(long uid) {
        phoneCompanyRepository.deleteById(uid);
    }

    @Override
    public Optional<PhoneCompany> findCompanyByUid(long uid) {
        return phoneCompanyRepository.findById(uid);
    }

    @Override
    public List<PhoneCompany> findAllCompanies() {
        return (List<PhoneCompany>) phoneCompanyRepository.findAll();
    }
}
