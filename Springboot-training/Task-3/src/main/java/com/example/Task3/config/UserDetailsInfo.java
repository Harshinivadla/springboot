package com.example.Task3.config;

import com.example.Task3.Model.UserInfo;
import com.example.Task3.constants.Constant;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailsInfo implements UserDetails {

    private String emailOrPhNo;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserDetailsInfo(UserInfo userInfo) {
        emailOrPhNo = getEmailOrPhNo(userInfo);
        password = userInfo.getPassword();
        authorities = Arrays.stream(userInfo.getRole().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    private String getEmailOrPhNo(UserInfo userInfo) {
        if (isValidEmail(userInfo.getEmail())) {
            return userInfo.getEmail();
        } else {
            return userInfo.getPhNo();
        }
    }

    private boolean isValidEmail(String email) {
        return email.matches(Constant.EMAIL_REG);

    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return emailOrPhNo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
