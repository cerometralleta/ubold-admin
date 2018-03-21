package com.ubold.admin.provider;

import com.ubold.admin.domain.UserInfo;
import com.ubold.admin.vo.SessionInfo;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;

/**
 * 自定义身份认证验证组件
 * Created by ningzuokun on 2017/12/18.
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final String AUTH_WRITE = "AUTH_WRITE";

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        // 获取认证的用户名 & 密码
        SessionInfo sessionInfo = this.createSessionInfo(username, password);
        boolean isTrue = (null != sessionInfo);
        // 认证逻辑
        if (isTrue) {
            // 这里设置权限和角色
            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(ROLE_ADMIN));
            authorities.add(new SimpleGrantedAuthority(AUTH_WRITE));
            // 生成令牌
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    username, password, authorities);
            //存储用户详细信息
            usernamePasswordAuthenticationToken.setDetails(sessionInfo);
            return usernamePasswordAuthenticationToken;
        }else {
            throw new BadCredentialsException("密码错误~");
        }
    }

    protected SessionInfo createSessionInfo(String userName, String password) {
        UserInfo userInfo = new UserInfo();
        if (userName.equals("admin") && password.equals("123456")) {
            userInfo.setId("016233fbde5ajTXPPT30kgiCgKXf5100");
            userInfo.setUsername("administrator");
        }
        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setUsername(userInfo.getUsername());
        sessionInfo.setPassword(userInfo.getPassword());
        sessionInfo.setUserId(userInfo.getId());

        //TODO 获取用户权限
        return sessionInfo;
    }

    // 是否可以提供输入类型的认证服务
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}