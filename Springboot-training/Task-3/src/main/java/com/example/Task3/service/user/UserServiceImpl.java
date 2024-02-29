package com.example.Task3.service.user;

import com.example.Task3.Model.UserInfo;
import com.example.Task3.constants.Constant;
import com.example.Task3.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserInfo findByEmailOrPhNo(String emailOrPhNo) {
        logger.info(Constant.FINDING_USER_BY_EMAIL_OR_PHONE_NUMBER, emailOrPhNo);
        return userRepository.findByEmailOrPhNo(emailOrPhNo);
    }

    @Override
    public String addUserDetails(UserInfo userInfo) {
        logger.info(Constant.ADDING_USER_DETAILS, userInfo);
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userRepository.save(userInfo);
        logger.info(Constant.USER_DETAILS_ADDED_SUCCESSFULLY);
        return Constant. USER_DETAILS_ADDED_SUCCESSFULLY;
    }
}
