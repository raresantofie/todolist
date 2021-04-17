package com.todlist.todlist.config;

import com.todlist.todlist.model.Role;
import com.todlist.todlist.model.User;
import com.todlist.todlist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserAuthenticationService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserAuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User with name %s not found", username));
        }
        return mapUserToUserDetails(user);
    }

    private UserDetails mapUserToUserDetails(User user) {
        List<GrantedAuthority> userAuthorities = mapRoleToGrantedAuthority(user.getRole());
        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), userAuthorities);
        return userDetails;
    }

    private List<GrantedAuthority> mapRoleToGrantedAuthority(Collection<Role> roleList) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Role role: roleList) {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getRole());
            grantedAuthorities.add(simpleGrantedAuthority);
        }
        return grantedAuthorities;
    }
}
