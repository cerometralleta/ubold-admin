package com.ubold.admin.service;

import com.ubold.admin.domain.UserInfo;
import com.ubold.admin.response.Response;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by lenovo on 2017/11/10.
 */
public interface UserService {

    UserInfo findByUserName(String userName);

    Response<UserInfo> findByUserNameAndPassword(String userName, String password);

    //动态SQL查询
    List<UserInfo> customQueryList(String userName,String password);

    Page<UserInfo> customQueryListPage(String userName, String password);

}
