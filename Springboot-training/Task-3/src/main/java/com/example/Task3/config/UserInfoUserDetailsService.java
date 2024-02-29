package com.example.Task3.config;

import com.example.Task3.Model.UserInfo;
import com.example.Task3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String emailOrPhNo) throws UsernameNotFoundException {
        UserInfo user = repository.findByEmailOrPhNo(emailOrPhNo);
        if (user != null) {
            return new UserDetailsInfo(user);//authentication
        } else {
            throw new UsernameNotFoundException("User not found with email or phone number: " + emailOrPhNo);
        }
    }
}
