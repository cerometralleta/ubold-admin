package com.ubold.admin.security;

import com.ubold.admin.constant.PermitPrefixURI;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsUtils;

/**
 * Created by ningzuokun on 2018/1/4.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .requestMatchers(CorsUtils::isPreFlightRequest) //对preflight放行
                .antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers("/**" + PermitPrefixURI.permit + "/**").permitAll()
                .antMatchers("/login", "/oauth/authorize").permitAll()
                .anyRequest()
                .authenticated();
    }
}
