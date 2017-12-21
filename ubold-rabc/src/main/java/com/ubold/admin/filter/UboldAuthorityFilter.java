package com.ubold.admin.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 功能过滤
 * Created by ningzuokun on 2017/12/21.
 */
public class UboldAuthorityFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //url 过滤
        //spring param获取header参数值

    }

    @Override
    public void destroy() {

    }
}
