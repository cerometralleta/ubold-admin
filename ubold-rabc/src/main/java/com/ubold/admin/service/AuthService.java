package com.ubold.admin.service;

import com.ubold.admin.response.Response;
import com.ubold.admin.vo.LoginParam;

/**
 * Created by ningzuokun on 2017/11/22.
 */
public interface AuthService {

    /**
     * 登录
     * @param loginParam
     * @return
     */
    public Response login(LoginParam loginParam);

    /**
     * 密码授权
     * @param loginParam
     * @return
     */
    public Response passwordLogin(LoginParam loginParam) throws Exception;
}
