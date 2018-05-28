package com.ubold.admin.service.impl;

import com.ubold.admin.domain.User;
import com.ubold.admin.domain.UserInfo;
import com.ubold.admin.model.AccountCredentials;
import com.ubold.admin.model.GetMenuResult;
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


        //获取用户菜单
        AccountCredentials accountCredentials = new AccountCredentials();
        accountCredentials.setToken(JWToken);

        //登录返回菜单列表
        Response<GetMenuResult> resultResponse = resourceService.getMenuItems(user.getId());
        accountCredentials.setMenu(resultResponse.getResult().getResources());

        AccountCredentials.App  app = new AccountCredentials.App();
        app.setName("Ubold");
        app.setDescription("Ng-zorro admin panel front-end framework");
        accountCredentials.setApp(app);
        return accountCredentials;
    }
}
