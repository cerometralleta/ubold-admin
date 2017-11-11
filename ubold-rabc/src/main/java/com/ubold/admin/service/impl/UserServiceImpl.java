package com.ubold.admin.service.impl;

import com.ubold.admin.domain.User;
import com.ubold.admin.repository.UserRepository;
import com.ubold.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lenovo on 2017/11/10.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }
}
