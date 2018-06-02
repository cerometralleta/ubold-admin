package com.ubold.admin.service.impl;

import com.ubold.admin.domain.UserInfo;
import com.ubold.admin.repository.UserRepository;
import com.ubold.admin.response.Response;
import com.ubold.admin.service.ResourceService;
import com.ubold.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lenovo on 2017/11/10.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    ResourceService resourceService;

    @Override
    public UserInfo findByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public Response<UserInfo> findByUserNameAndPassword(String userName, String password) {
        UserInfo userInfo = userRepository.findByUsernameAndPassword(userName, password);
        if (null == userInfo) {
            return Response.FAILURE();
        }
        return Response.SUCCESS(userInfo);
    }
}
