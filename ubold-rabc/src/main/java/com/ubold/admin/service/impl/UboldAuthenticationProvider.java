package com.ubold.admin.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * AuthenticationManager 是通过它来认证用户的。
 * Created by ningzuokun on 2017/11/10.
 */
@Component
public class UboldAuthenticationProvider implements AuthenticationProvider {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    UboldUserDetailsService uboldUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserDetails user = uboldUserDetailsService.loadUserByUsername(username);
        logger.info("password={}, needPassword={}", password, user.getPassword());
        //密码匹配验证
        if (passwordEncoder().matches(password, user.getPassword())) {
            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
            return new UsernamePasswordAuthenticationToken(user, password, authorities);
        }
        throw new BadCredentialsException("Wrong password.");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
