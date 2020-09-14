package com.example.phonebook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    @Column(unique = true)
    private String name;

    @Transient
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(){}

    public Role(long uid) {
        this.uid = uid;
    }

    public Role(String name) {
        this.name = name;
    }

    public Role(long uid, String name) {
        this.uid = uid;
        this.name = name;
    }

    public long getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getAuthority() {
        return name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "uid=" + uid +
                ", authority='" + name + '\'' +
                '}';
    }
}
