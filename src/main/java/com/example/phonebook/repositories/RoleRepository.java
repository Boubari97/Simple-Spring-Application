package com.example.phonebook.repositories;

import com.example.phonebook.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Set<Role> findAll();
}
