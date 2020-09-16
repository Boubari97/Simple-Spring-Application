package com.example.phonebook.controllers;

import com.example.phonebook.model.PhoneCompany;
import com.example.phonebook.model.PhoneNumber;
import com.example.phonebook.model.User;
import com.example.phonebook.model.UserAccount;
import com.example.phonebook.services.PhoneCompanyService;
import com.example.phonebook.services.PhoneNumberService;
import com.example.phonebook.services.UserAccountService;
import com.example.phonebook.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Optional;

@Controller
public class PhoneNumberController {

    private final PhoneNumberService phoneNumberService;
    private final UserService userService;
    private final PhoneCompanyService phoneCompanyService;
    private final UserAccountService userAccountService;

    @Autowired
    public PhoneNumberController(PhoneNumberService phoneNumberService, UserService userService,
                                 PhoneCompanyService phoneCompanyService, UserAccountService userAccountService) {
        this.phoneNumberService = phoneNumberService;
        this.userService = userService;
        this.phoneCompanyService = phoneCompanyService;
        this.userAccountService = userAccountService;
    }

    @GetMapping(value = {"/number", "/number/{uid}"})
    public String showNumberPage(@PathVariable(value = "uid", required = false) Long uid, Model model, Principal principal) {
        if (uid != null) {
            Optional<PhoneNumber> optionalPhoneNumber = phoneNumberService.findNumberByUid(uid);
            if (optionalPhoneNumber.isPresent()) {
                PhoneNumber number = optionalPhoneNumber.get();
                model.addAttribute("number", number);
            }
        }
        model.addAttribute("username", principal.getName());
        return "numberPage.ftlh";
    }

    @PostMapping(value = "/number")
    public String saveOrUpdateNumber(@RequestParam("number") long number,
                                   @RequestParam("companyUid") long companyUid,
                                   Principal principal) {
        User user = (User) userService.loadUserByUsername(principal.getName());
        Optional<PhoneCompany> phoneCompany = phoneCompanyService.findCompanyByUid(companyUid);

        BigDecimal startBalance = new BigDecimal(50 + Math.random()*100 );
        UserAccount userAccount = new UserAccount(startBalance, phoneCompany.get());

        PhoneNumber phoneNumber = new PhoneNumber(number, user, phoneCompany.get(), userAccount);
        phoneNumberService.saveNumber(phoneNumber);
        return "redirect:/profile";
    }
}
