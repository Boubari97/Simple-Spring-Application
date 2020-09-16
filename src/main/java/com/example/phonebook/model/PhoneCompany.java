package com.example.phonebook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "phone_companies")
public class PhoneCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    @Column(unique = true)
    private String name;

    @Transient
    @JsonIgnore
    @OneToMany(mappedBy = "phoneCompany", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PhoneNumber> numbers;

    @Transient
    @JsonIgnore
    @OneToMany(mappedBy = "phoneCompany", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserAccount> accounts;

    public PhoneCompany() {
    }

    public PhoneCompany(String name, List<PhoneNumber> numbers, Set<UserAccount> accounts) {
        this.name = name;
        this.numbers = numbers;
        this.accounts = accounts;
    }

    public PhoneCompany(long uid, String name, List<PhoneNumber> numbers, Set<UserAccount> accounts) {
        this.uid = uid;
        this.name = name;
        this.numbers = numbers;
        this.accounts = accounts;
    }

    public long getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public List<PhoneNumber> getNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        return "PhoneCompany{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                '}';
    }
}
