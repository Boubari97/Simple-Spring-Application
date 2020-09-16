package com.example.phonebook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "user_accounts")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    @Column(nullable = false)
    private BigDecimal balance;

    @JsonIgnore
    @Transient
    @OneToOne(mappedBy = "userAccount", optional = false)
    private PhoneNumber number;

    @ManyToOne(optional = false)
    @JoinColumn(name = "phone_company_uid", nullable = false)
    private PhoneCompany phoneCompany;

    public UserAccount() {

    }

    public UserAccount(BigDecimal balance, PhoneCompany phoneCompany) {
        this.balance = balance;
        this.phoneCompany = phoneCompany;
    }

    public UserAccount(BigDecimal balance, PhoneNumber number, PhoneCompany phoneCompany) {
        this.balance = balance;
        this.number = number;
        this.phoneCompany = phoneCompany;
    }

    public UserAccount(long uid, BigDecimal balance, PhoneNumber number, PhoneCompany phoneCompany) {
        this.uid = uid;
        this.balance = balance;
        this.number = number;
        this.phoneCompany = phoneCompany;
    }

    public void provideService(BigDecimal priceForService) {
        balance = balance.subtract(priceForService);
    }

    public long getUid() {
        return uid;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public PhoneNumber getNumber() {
        return number;
    }

    public PhoneCompany getPhoneCompany() {
        return phoneCompany;
    }

    public void setNumber(PhoneNumber number) {
        this.number = number;
    }

    public void setPhoneCompany(PhoneCompany phoneCompany) {
        this.phoneCompany = phoneCompany;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "uid=" + uid +
                ", balance=" + balance +
                ", number=" + number.getNumber() +
                ", phoneCompany=" + phoneCompany.getName() +
                '}';
    }
}
