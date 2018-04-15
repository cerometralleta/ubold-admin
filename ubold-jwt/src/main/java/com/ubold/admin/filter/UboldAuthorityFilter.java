package com.ubold.admin.filter;

import com.ubold.admin.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import java.io.IOException;

/**
 * 功能过滤
 * Created by ningzuokun on 2017/12/21.
 */
public class UboldAuthorityFilter implements Filter {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    ResourceService resourceService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
