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
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "phone_user")
    private User phoneUser;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "phone_company_uid", nullable = false)
    private PhoneCompany phoneCompany;

    public PhoneNumber() {
    }

    public PhoneNumber(long number, User phoneUser, PhoneCompany phoneCompany) {
        this.number = number;
        this.phoneUser = phoneUser;
        this.phoneCompany = phoneCompany;
    }

    public PhoneNumber(long uid, long number, User phoneUser, PhoneCompany phoneCompany) {
        this.uid = uid;
        this.number = number;
        this.phoneUser = phoneUser;
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
