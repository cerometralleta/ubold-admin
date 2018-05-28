package com.ubold.admin.service.impl;

import com.ubold.admin.domain.UserInfo;
import com.ubold.admin.model.GetMenuResult;
import com.ubold.admin.response.Response;
import com.ubold.admin.service.JwtLoginService;
import com.ubold.admin.service.ResourceService;
import com.ubold.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List getMenuItems(String userId) {
        Response<GetMenuResult> resultResponse = resourceService.getMenuList(userId);
        return resultResponse.getResult().getResources();
    }

    @Override
    public Map<String, String> getAuthority(String userId) {
        return resourceService.getAuthority(userId);
    }
}
