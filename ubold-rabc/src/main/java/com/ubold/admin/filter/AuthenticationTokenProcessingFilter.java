package com.ubold.admin.filter;

import com.ubold.admin.domain.UserDetailContext;
import com.ubold.admin.utils.AuthTokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by ningzuokun on 2017/11/14.
 */
@Component
public class AuthenticationTokenProcessingFilter extends GenericFilterBean {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * Contains UserDetailsService that is injected from security configuration.
     */
    @Autowired
    UserDetailsService userService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("System.out-AuthenticationTokenProcessingFilter::doFilter::BEGIN");
        // cast request into an HttpServletRequest
        HttpServletRequest httpRequest = this.getAsHttpRequest(servletRequest);

        // Extract authToken and username
        String authToken = this.extractAuthTokenFromRequest(httpRequest);
        String username = httpRequest.getParameter("username");
        logger.info("System.out-AuthenticationTokenProcessingFilter::doFilter::authToken={},username={}" , authToken,username);
        // load user with supplied username
        UserDetailContext userSessionContext = null;
        // Challenge supplied token with actual token in userDetails. Halt if it isn't valid
        if(!AuthTokenUtils.validateToken(authToken,userSessionContext)){

            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);
        logger.info("AuthenticationTokenProcessingFilter::doFilter::END");
    }

    /**
     * Cast request into HttpServletRequest.
     *
     * @param request ServletRequest
     * @return HttpServletRequest
     */
    private HttpServletRequest getAsHttpRequest(ServletRequest request) {
        if (!(request instanceof HttpServletRequest)) {
            throw new RuntimeException("Expecting an HTTP Request.");
        }

        return (HttpServletRequest) request;
    }

    /**
     * Extract authToken from supplied request.
     *
     * @param httpRequest HttpServletRequest
     * @return String authToken extracted from httpRequest
     */
    private String extractAuthTokenFromRequest(HttpServletRequest httpRequest) {
        // Get token from Header
        String authToken = httpRequest.getHeader("X-Auth-Token");

        // If token can't be found than get it from the request parameter
        if (authToken == null) {
            authToken = httpRequest.getParameter("auth_token");
        }

        return authToken;
    }
}
