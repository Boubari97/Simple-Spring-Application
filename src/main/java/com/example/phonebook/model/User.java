package com.example.phonebook.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @JoinColumn(name = "role_uid", nullable = false, columnDefinition = "BIGINT default = 1")
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "phoneUser", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PhoneNumber> phoneNumbers;

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, PhoneNumber phoneNumbers) {
        this.username = username;
        this.password = password;
        this.phoneNumbers = new ArrayList<>();
        this.phoneNumbers.add(phoneNumbers);
    }

    public User(String username, String password, List<PhoneNumber> phoneNumbers) {
        this.username = username;
        this.password = password;
        this.phoneNumbers = phoneNumbers;
    }

    public User(long uid, String username, String password, List<PhoneNumber> phoneNumbers, Set<Role> roles) {
        this.uid = uid;
        this.roles = roles;
        this.username = username;
        this.password = password;
        this.phoneNumbers = phoneNumbers;
    }

    public long getUid() {
        return uid;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", phoneNumbers=" + phoneNumbers +
                '}';
    }
}
