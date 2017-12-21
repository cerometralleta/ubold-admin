package com.ubold.admin.service;

import com.ubold.admin.domain.UserInfo;
import com.ubold.admin.response.Response;
import com.ubold.admin.vo.AuthorizeResult;
import com.ubold.admin.vo.LoginParam;

/**
 * Created by ningzuokun on 2017/11/22.
 */
public interface AuthService {

    /**
     * 密码授权
     * @param loginParam
     * @return
     */
     Response<UserInfo> ClientPasswordLogin(LoginParam loginParam) throws Exception;

    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
     Response<UserInfo> getUserInfoByToken(String token);
}
