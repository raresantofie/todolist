package com.todlist.todlist.controller;

import com.todlist.todlist.controller.dtos.RequestUserDto;
import com.todlist.todlist.exceptions.ResourceNotFoundException;
import com.todlist.todlist.model.Role;
import com.todlist.todlist.model.User;
import com.todlist.todlist.services.RoleService;
import com.todlist.todlist.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@RestController
public class UserController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping(path = "/register")
    public User create(@RequestBody RequestUserDto requestUserDto) {
        User user = mapRequestUserDtoToUser(requestUserDto);
        return userService.create(user);
    }

    @GetMapping(path = "/logged-user")
    public Principal getLoggedUsername(Principal principal){
        return principal;
    }

    // map{fromType}to{Type}
    public User mapRequestUserDtoToUser(RequestUserDto requestUserDto) {
        User user = new User();
        user.setUsername(requestUserDto.getUsername());
        user.setPassword(requestUserDto.getPassword());
        Role role = roleService.findByRoleName(requestUserDto.getRoleName());
        if (role == null) {
            throw new ResourceNotFoundException(String.format("Role with name %s not found", requestUserDto.getRoleName()));
        }
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        return user;
    }

}
