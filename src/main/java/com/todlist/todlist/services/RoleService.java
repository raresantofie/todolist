package com.todlist.todlist.services;

import com.todlist.todlist.model.Role;

public interface RoleService {
    Role create(Role role);
    Role findByRoleName(String roleName);
}
