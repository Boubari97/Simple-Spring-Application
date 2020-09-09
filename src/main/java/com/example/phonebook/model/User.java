package com.example.phonebook.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    @Column(unique = true, nullable = false)
    private String username;

    @OneToMany(mappedBy = "phoneUser", fetch = FetchType.EAGER)
    private List<PhoneNumber> phoneNumbers;

    public User() {

    }

    public User(String username, List<PhoneNumber> phoneNumbers) {
        this.username = username;
        this.phoneNumbers = phoneNumbers;
    }

    public User(long uid, String username, List<PhoneNumber> phoneNumbers) {
        this.uid = uid;
        this.username = username;
        this.phoneNumbers = phoneNumbers;
    }

    public long getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }
}
