package com.todlist.todlist.repositories;

import com.todlist.todlist.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String roleName);
}
