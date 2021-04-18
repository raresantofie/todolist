package com.todlist.todlist.services;

import com.todlist.todlist.exceptions.DatabaseConstraintException;
import com.todlist.todlist.model.Role;
import com.todlist.todlist.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleServiceImplementation implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImplementation(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role create(Role role) {
        try {
            return roleRepository.save(role);
        } catch (Exception e) {
            throw new DatabaseConstraintException(e.getCause().getCause().getLocalizedMessage());
        }
    }

    @Override
    public Role findByRoleName(String roleName) {
        return roleRepository.findByRole(roleName);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
