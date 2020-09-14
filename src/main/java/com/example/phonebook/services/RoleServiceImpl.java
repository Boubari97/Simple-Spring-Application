package com.example.phonebook.services;

import com.example.phonebook.model.Role;
import com.example.phonebook.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Role> findRoleByUid(long uid) {
        return roleRepository.findById(uid);
    }

    @Override
    public Set<Role> findAllRoles() {
        return roleRepository.findAll();
    }
}
