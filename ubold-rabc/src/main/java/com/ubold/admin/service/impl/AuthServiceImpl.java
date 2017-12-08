package com.ubold.admin.service.impl;

import com.ubold.admin.constant.StatusCodeConstant;
import com.ubold.admin.domain.JwtUser;
import com.ubold.admin.response.Response;
import com.ubold.admin.security.JwtTokenUtil;
import com.ubold.admin.security.UboldUserDetailsService;
import com.ubold.admin.service.AuthService;
import com.ubold.admin.service.PermissionService;
import com.ubold.admin.vo.LoginParam;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ningzuokun on 2017/11/22.
 */
@Service
public class AuthServiceImpl implements AuthService {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UboldUserDetailsService uboldUserDetailsService;

    @Autowired
    PermissionService permissionService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public Response login(LoginParam loginParam) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(loginParam.getUsername(), loginParam.getPassword());
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        return Response.SUCCESS(jwtUser.getAuthToken());
    }
}