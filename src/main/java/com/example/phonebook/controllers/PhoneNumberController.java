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
        String formAction = "/number/";
        if (uid != null) {
            Optional<PhoneNumber> phoneNumber = phoneNumberService.findNumberByUid(uid);
            phoneNumber.ifPresent(number -> model.addAttribute("number", number));
            formAction += uid;
        }
        model.addAttribute("formAction", formAction);
        model.addAttribute("username", principal.getName());
        return "numberPage.ftlh";
    }

    @PostMapping(value = "/number")
    public String saveNumber(@RequestParam(value = "number", required = false) Long number,
                             @RequestParam("companyUid") Long companyUid, Principal principal) {
        Optional<PhoneCompany> phoneCompany = phoneCompanyService.findCompanyByUid(companyUid);
        if (phoneCompany.isPresent()) {
            User user = (User) userService.loadUserByUsername(principal.getName());
            BigDecimal startBalance = BigDecimal.valueOf(50 + Math.random() * 100);
            UserAccount userAccount = new UserAccount(startBalance, phoneCompany.get());
            PhoneNumber phoneNumber = new PhoneNumber(number, user, phoneCompany.get(), userAccount);
            phoneNumberService.saveNumber(phoneNumber);
        }
        return "redirect:/profile";
    }

    @PostMapping(value = "/number/{uid}")
    public String updateNumber(@PathVariable("uid") long uid,
                               @RequestParam("companyUid") Long companyUid) {
        Optional<PhoneCompany> phoneCompany = phoneCompanyService.findCompanyByUid(companyUid);
        Optional<PhoneNumber> phoneNumber = phoneNumberService.findNumberByUid(uid);
        if (phoneNumber.isPresent() && phoneCompany.isPresent()) {
            userAccountService.changeMobileOperator(phoneNumber.get(), phoneCompany.get());
        }
        return "redirect:/profile";
    }
}
