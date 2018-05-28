package com.ubold.admin.service;

import com.ubold.admin.domain.User;
import com.ubold.admin.response.Response;

import java.io.Serializable;
import java.util.Map;

public interface JwtLoginService {

    //用户验证
    Response<? extends User> findByUserNameAndPassword(String userName, String password);

    //根据userid获取所有授权资源key url
    Map<String, String> getResources(String userId);

    //登陆后账户凭据
    Serializable getAccountCredentials(User user, String JWToken);
}
