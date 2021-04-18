package com.todlist.todlist.controller;

import com.todlist.todlist.model.Role;
import com.todlist.todlist.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {

    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping(path = "/roles")
    public Role create(@RequestBody Role role) {
        return roleService.create(role);
    }

    @GetMapping(path = "/roles/{roleName}")
    public Role findByRoleName(@PathVariable String roleName) {
        return roleService.findByRoleName(roleName);
    }

    @GetMapping(path = "/roles")
    public List<Role> findAll() {
        return roleService.findAll();
    }
}
