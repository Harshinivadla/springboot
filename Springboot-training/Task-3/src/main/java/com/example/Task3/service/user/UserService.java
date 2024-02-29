package com.example.Task3.service.user;

import com.example.Task3.Model.UserInfo;

public interface UserService {
        UserInfo findByEmailOrPhNo(String emailOrPhNo);
        String addUserDetails(UserInfo userInfo);
    }

