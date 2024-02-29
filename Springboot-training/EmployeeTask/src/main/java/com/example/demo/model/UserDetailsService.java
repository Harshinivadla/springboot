package com.example.demo.model;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.exception.EmployeeException;

@Service
public class UserDetailsService {

    public UserDetails loadUserByUsername(String username) throws EmployeeException {
        return User.withUsername("user")
                .password("{plainText}password")
                .roles("USER")
                .build();
    }
}
