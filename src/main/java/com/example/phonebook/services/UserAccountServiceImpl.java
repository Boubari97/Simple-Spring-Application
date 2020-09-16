package com.example.phonebook.services;

import com.example.phonebook.model.PhoneCompany;
import com.example.phonebook.model.PhoneNumber;
import com.example.phonebook.model.UserAccount;
import com.example.phonebook.repositories.PhoneNumberRepository;
import com.example.phonebook.repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.math.BigDecimal;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userAccountRepository;
    private final PhoneNumberRepository phoneNumberRepository;
    private final DefaultTransactionDefinition defaultTransactionDefinition;
    private final PlatformTransactionManager transactionManager;

    @Autowired
    public UserAccountServiceImpl(UserAccountRepository userAccountRepository,
                                  PhoneNumberRepository phoneNumberRepository,
                                  DefaultTransactionDefinition defaultTransactionDefinition,
                                  PlatformTransactionManager transactionManager) {
        this.userAccountRepository = userAccountRepository;
        this.defaultTransactionDefinition = defaultTransactionDefinition;
        this.transactionManager = transactionManager;
        this.phoneNumberRepository = phoneNumberRepository;
    }

    @Override
    public void saveUserAccount(UserAccount userAccount) {
        userAccountRepository.save(userAccount);
    }

    @Override
    public void deleteUserAccountByUid(long uid) {
        userAccountRepository.deleteById(uid);
    }

    @Override
    public boolean changeMobileOperator(PhoneNumber phoneNumber, PhoneCompany newMobileOperator) {
        boolean success = false;
        TransactionStatus status = transactionManager.getTransaction(defaultTransactionDefinition);
        try {
            BigDecimal priceForChange = new BigDecimal(99);
            UserAccount userAccount = phoneNumber.getUserAccount();
            if (userAccount.getBalance().compareTo(priceForChange) < 0) {
                throw new RuntimeException("Not enough money");
            }
            phoneNumber.setPhoneCompany(newMobileOperator);
            userAccount.setPhoneCompany(newMobileOperator);
            userAccount.provideService(priceForChange);
            userAccountRepository.save(userAccount);
            phoneNumberRepository.save(phoneNumber);
            transactionManager.commit(status);
            success = true;
        } catch (Exception exception) {
            transactionManager.rollback(status);
        }
        return success;
    }
}
