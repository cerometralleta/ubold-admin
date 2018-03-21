package com.ubold.admin.filter;

import com.ubold.admin.service.TokenAuthenticationService;
import com.ubold.admin.util.SpringContextUtil;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 拦截所有需要JWT的请求，然后调用TokenAuthenticationService类的静态方法去做JWT验证
 * Created by ningzuokun on 2017/12/18.
 */
public class JWTAuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain)
            throws IOException, ServletException {
        TokenAuthenticationService tokenAuthenticationService = SpringContextUtil.getBean(TokenAuthenticationService.class);
        Authentication authentication = tokenAuthenticationService.getAuthentication((HttpServletRequest)request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request,response);
    }
}