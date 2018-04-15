package com.ubold.admin.provider;

import com.ubold.admin.response.Response;
import com.ubold.admin.service.TokenAuthenticationService;
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
        TokenAuthenticationService tokenAuthenticationService = SpringContextUtil.getBean(TokenAuthenticationService.class);
        Response<TokenInfo> tokenInfoResponse = tokenAuthenticationService.createTokenInfo(username, password);
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


    // 是否可以提供输入类型的认证服务
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}