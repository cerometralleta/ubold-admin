package com.ubold.admin.service;

import com.ubold.admin.domain.UserInfo;

import java.util.List;

/**
 * Created by lenovo on 2017/11/10.
 */
public interface UserService {

    List<UserInfo> findByUserName(String userName);
}
