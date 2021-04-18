package com.todlist.todlist.services;

import com.todlist.todlist.model.Role;

import java.util.List;

public interface RoleService {
    Role create(Role role);
    Role findByRoleName(String roleName);
    List<Role> findAll();
}
