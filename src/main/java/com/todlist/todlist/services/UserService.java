package com.todlist.todlist.services;

import com.todlist.todlist.model.User;

public interface UserService {

    User create(User user);
    User findByUsername(String username);
    User update(User user);
    void delete(User user);
    User findById(Long id);
}
