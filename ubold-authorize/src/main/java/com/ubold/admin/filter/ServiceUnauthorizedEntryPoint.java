package com.ubold.admin.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  当访问权限验证失败是，根据Restful规范返回401 Unauthorized，
 *  因此需要设定entry-point-ref，重新指向一个自定义的entrypoint
 * Created by ningzuokun on 2017/11/13.
 */
public class ServiceUnauthorizedEntryPoint implements AuthenticationEntryPoint {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        // return 401 UNAUTHORIZED status code if the user is not authenticated
        logger.debug(" *** UnauthorizedEntryPoint.commence: " + httpServletRequest.getRequestURI());
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
