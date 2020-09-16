package com.example.phonebook.services;

import com.example.phonebook.model.PhoneCompany;
import com.example.phonebook.model.PhoneNumber;
import com.example.phonebook.model.UserAccount;

public interface UserAccountService {

    void saveUserAccount(UserAccount userAccount);

    void deleteUserAccountByUid(long uid);

    boolean changeMobileOperator(PhoneNumber phoneNumber, PhoneCompany newMobileOperator);
}
