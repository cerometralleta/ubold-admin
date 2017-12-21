package com.ubold.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ubold.admin.config.SecurityOaut2Configure;
import com.ubold.admin.domain.UserInfo;
import com.ubold.admin.response.Response;
import com.ubold.admin.service.AuthService;
import com.ubold.admin.service.PermissionService;
import com.ubold.admin.service.UserService;
import com.ubold.admin.util.HttpClientUtils;
import com.ubold.admin.vo.ClaimsResult;
import com.ubold.admin.vo.LoginParam;
import com.ubold.admin.vo.Oauth2Result;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ningzuokun on 2017/11/22.
 */
@Service
public class AuthServiceImpl implements AuthService {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    PermissionService permissionService;

    @Autowired
    UserService userService;

    @Autowired
    SecurityOaut2Configure securityOaut2Configure;

    @Override
    public Response ClientPasswordLogin(LoginParam loginParam) throws Exception {
        String oauth2Path = securityOaut2Configure.getAuthorityContext() +
                            "/oauth/token?" +
                            "username=" + loginParam.getUsername()+
                            "&password=" + loginParam.getPassword()+
                            "&grant_type=password&scope=user_info";
        String result = HttpClientUtils.doPost(oauth2Path);
        logger.info("授权结果:{}",result);
        Oauth2Result oauth2Result = JSONObject.parseObject(result, Oauth2Result.class);

        //get userinfo and cache token
        this.getUserInfoByToken(oauth2Result.getAccess_token());
        return Response.SUCCESS(oauth2Result.getAccess_token());
    }

    @Override
    public Response<UserInfo> getUserInfoByToken(String token) {
        Jwt jwt = JwtHelper.decode(token);
        ClaimsResult claimsParam = JSONObject.parseObject(jwt.getClaims(),ClaimsResult.class);
        List<UserInfo> userInfoList = userService.findByUserName(claimsParam.getUser_name());
        if(CollectionUtils.isNotEmpty(userInfoList)){
            return Response.SUCCESS(userInfoList.get(0));
        }
        return Response.FAILURE();
    }
}
