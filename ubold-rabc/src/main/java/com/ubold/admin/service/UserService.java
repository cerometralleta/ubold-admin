package com.ubold.admin.service;

import com.ubold.admin.domain.UserInfo;
import com.ubold.admin.response.Response;

/**
 * Created by lenovo on 2017/11/10.
 */
public interface UserService {

    UserInfo findByUserName(String userName);

    Response<UserInfo> findByUserNameAndPassword(String userName, String password);
}
