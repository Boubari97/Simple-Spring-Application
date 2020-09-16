package com.example.phonebook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "phone_numbers")
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    @Column(unique = true, nullable = false)
    private long number;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "phone_user")
    private User phoneUser;

    @ManyToOne(optional = false)
    @JoinColumn(name = "phone_company_uid", nullable = false)
    private PhoneCompany phoneCompany;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_account_uid", nullable = true)
    private UserAccount userAccount;

    public PhoneNumber() {
    }

    public PhoneNumber(long number, User phoneUser, PhoneCompany phoneCompany, UserAccount userAccount) {
        this.number = number;
        this.phoneUser = phoneUser;
        this.phoneCompany = phoneCompany;
        this.userAccount = userAccount;
    }

    public PhoneNumber(long number, User phoneUser, PhoneCompany phoneCompany) {
        this.number = number;
        this.phoneUser = phoneUser;
        this.phoneCompany = phoneCompany;
    }

    public PhoneNumber(long uid, long number, User phoneUser, PhoneCompany phoneCompany, UserAccount userAccount) {
        this.uid = uid;
        this.number = number;
        this.phoneUser = phoneUser;
        this.phoneCompany = phoneCompany;
        this.userAccount = userAccount;
    }

    public void setPhoneCompany(PhoneCompany phoneCompany) {
        this.phoneCompany = phoneCompany;
    }

    public long getUid() {
        return uid;
    }

    public long getNumber() {
        return number;
    }

    public User getPhoneUser() {
        return phoneUser;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public PhoneCompany getPhoneCompany() {
        return phoneCompany;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "number=" + number +
                ", phoneCompany=" + phoneCompany.getName() +
                '}';
    }
}
