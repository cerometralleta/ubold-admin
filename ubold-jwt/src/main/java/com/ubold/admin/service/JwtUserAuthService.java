package com.ubold.admin.service;

import com.ubold.admin.domain.User;
import com.ubold.admin.model.GetMenuResult;
import com.ubold.admin.response.Response;

import java.util.Map;

public interface JwtUserAuthService {

    //用户验证
    Response<User> findByUserNameAndPassword(String userName, String password);

    //根据userid获取授权菜单
    Response<GetMenuResult> getMenuItems(String userId);

    //根据userid获取所有授权资源
    Map<String, String> getAuthority(String userId);
}
