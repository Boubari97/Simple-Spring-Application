package com.example.phonebook.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "phone_companies")
public class PhoneCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "phoneCompany", fetch = FetchType.LAZY)
    private List<PhoneNumber> numbers;

    public PhoneCompany() {
    }

    public PhoneCompany(String name, List<PhoneNumber> numbers) {
        this.name = name;
        this.numbers = numbers;
    }

    public PhoneCompany(long uid, String name, List<PhoneNumber> numbers) {
        this.uid = uid;
        this.name = name;
        this.numbers = numbers;
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
