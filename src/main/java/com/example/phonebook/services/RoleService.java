package com.example.phonebook.services;

import com.example.phonebook.model.Role;

import java.util.Optional;
import java.util.Set;

public interface RoleService {

    Optional<Role> findRoleByUid(long uid);
    Set<Role> findAllRoles();

}
