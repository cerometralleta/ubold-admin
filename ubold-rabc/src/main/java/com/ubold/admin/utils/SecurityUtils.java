package com.ubold.admin.utils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;

import java.util.Collection;

/**
 * Created by Kim on 2015/9/22.
 */
public class SecurityUtils {

    public static void login(String username, String password,Collection<? extends GrantedAuthority> authorities) {
        SecurityContextImpl securityContext = new SecurityContextImpl();
        securityContext.setAuthentication(new UsernamePasswordAuthenticationToken(username, password, authorities));
        SecurityContextHolder.setContext(securityContext);
    }

}
