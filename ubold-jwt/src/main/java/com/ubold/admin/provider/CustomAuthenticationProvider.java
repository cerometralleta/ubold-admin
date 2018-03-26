package com.ubold.admin.provider;

import com.ubold.admin.domain.UserInfo;
import com.ubold.admin.response.Response;
import com.ubold.admin.service.UserService;
import com.ubold.admin.util.SpringContextUtil;
import com.ubold.admin.vo.TokenInfo;

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
        Response<TokenInfo> tokenInfoResponse = this.createTokenInfo(username, password);
        // 认证逻辑
        if (tokenInfoResponse.checkSuccess()) {
            // 这里设置权限和角色
            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(ROLE_ADMIN));
            authorities.add(new SimpleGrantedAuthority(AUTH_WRITE));
            // 生成令牌
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(username, password, authorities);
            //存储用户详细信息
            usernamePasswordAuthenticationToken.setDetails(tokenInfoResponse.getResult());
            return usernamePasswordAuthenticationToken;
        }
        throw new BadCredentialsException("密码错误~");
    }

    protected Response<TokenInfo> createTokenInfo(String userName, String password) {
        UserService userService = SpringContextUtil.getBean(UserService.class);
        Response<UserInfo> response = userService.findByUserNameAndPassword(userName, password);
        if (!response.checkSuccess()) {
            return Response.FAILURE();
        }
        UserInfo userInfo = response.getResult();
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setUsername(userInfo.getUsername());
        tokenInfo.setUserId(userInfo.getId());
        tokenInfo.setPassword(userInfo.getPassword());
        return Response.SUCCESS(tokenInfo);
    }

    // 是否可以提供输入类型的认证服务
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}