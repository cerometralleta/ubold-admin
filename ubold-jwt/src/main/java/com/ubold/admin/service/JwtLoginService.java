package com.ubold.admin.service;

import com.ubold.admin.domain.User;
import com.ubold.admin.response.Response;

import java.util.List;
import java.util.Map;

public interface JwtLoginService {

    //用户验证
    Response<? extends User> findByUserNameAndPassword(String userName, String password);

    //根据userid获取授权菜单
    List getMenuItems(String userId);

    //根据userid获取所有授权资源
    Map<String, String> getAuthority(String userId);
}
