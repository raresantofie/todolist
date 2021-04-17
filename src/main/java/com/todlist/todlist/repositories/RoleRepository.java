package com.todlist.todlist.repositories;

import com.todlist.todlist.model.Role;
import org.springframework.data.repository.CrudRepository;


public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRole(String roleName);
}
