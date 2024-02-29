package com.example.Task3.controller;

import com.example.Task3.Model.UserInfo;
import com.example.Task3.service.jwt.JwtService;
import com.example.Task3.service.user.UserService;
import com.example.Task3.token.AuthRequest;
import jakarta.validation.Valid;
import com.example.Task3.constants.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/add")
    public String addNewUser(@Valid @RequestBody UserInfo userInfo) {
        log.info(Constant.RECEIVED_REQUEST_TO_ADD_NEW_USER, userInfo);
        return userService.addUserDetails(userInfo);
    }

    @PostMapping("/auth")
    public String authenticationRequest(@RequestBody AuthRequest authRequest) {
        log.info(Constant.RECEIVED_AUTHENTICATION_REQUEST, authRequest.getUserName());
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));

            if (authentication.isAuthenticated()) {
                log.info(Constant.USER_AUTHENTICATED_SUCCESSFULLY, authRequest.getUserName());
                return jwtService.generateToken(authRequest.getUserName());
            } else {
                log.error(Constant.AUTHENTICATION_FAILED, authRequest.getUserName());
                return Constant.AUTHENTICATION_FAILED_MESSAGE;
            }
        } catch (UsernameNotFoundException ex) {
            log.error(Constant.USER_NOT_FOUND, authRequest.getUserName());
            return Constant. USER_NOT_FOUND ;
        }
    }
}