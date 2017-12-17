package com.ubold.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ubold.admin.response.Response;
import com.ubold.admin.security.SecurityUser;
import com.ubold.admin.service.AuthService;
import com.ubold.admin.service.PermissionService;
import com.ubold.admin.util.HttpClientUtils;
import com.ubold.admin.vo.LoginParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by ningzuokun on 2017/11/22.
 */
@Service
public class AuthServiceImpl implements AuthService {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    PermissionService permissionService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public Response login(LoginParam loginParam) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(loginParam.getUsername(), loginParam.getPassword());
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        SecurityUser jwtUser = (SecurityUser) authentication.getPrincipal();
        return Response.SUCCESS(jwtUser.getAuthToken());
    }

    @Override
    public Response passwordLogin(LoginParam loginParam) throws Exception {
        String authcontextpath =
                "http://localhost:8081/oauth/token?"+
                        "username="+loginParam.getUsername()+"&password="+loginParam.getPassword()+
                        "&grant_type=password&client_id=ubold&client_secret=123456";
        String result = HttpClientUtils.doPost(authcontextpath);
        logger.info("授权结果:{}",result);
        JSONObject resultJson = JSONObject.parseObject(result);
        return Response.SUCCESS();
    }
}
