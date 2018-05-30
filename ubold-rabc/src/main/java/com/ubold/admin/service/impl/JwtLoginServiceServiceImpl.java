package com.ubold.admin.service.impl;

import com.ubold.admin.domain.User;
import com.ubold.admin.domain.UserInfo;
import com.ubold.admin.model.CredentialsModel;
import com.ubold.admin.response.Response;
import com.ubold.admin.service.JwtLoginService;
import com.ubold.admin.service.ResourceService;
import com.ubold.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Map;

@Service
public class JwtLoginServiceServiceImpl implements JwtLoginService {

    @Autowired
    ResourceService resourceService;

    @Autowired
    UserService userService;

    @Override
    public Response<UserInfo> findByUserNameAndPassword(String userName, String password) {
        return userService.findByUserNameAndPassword(userName,password);
    }

    @Override
    public Map<String, String> getResources(String userId) {
        return resourceService.getAuthority(userId);
    }

    @Override
    public Serializable getAccountCredentials(User user, String JWToken) {

        //返回jwt token
        CredentialsModel credentialsModel = new CredentialsModel();
        credentialsModel.setToken(JWToken);
        credentialsModel.setUser(user);
        return credentialsModel;
    }
}
