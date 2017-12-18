package com.ubold.admin;

import com.ubold.admin.constant.PermitPrefixURI;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;

@EnableOAuth2Sso
@Configuration
@Order(1)
public class ClientSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**")
            .authorizeRequests()
            .antMatchers("/", "/login**").permitAll()
            .antMatchers("/**/"+ PermitPrefixURI.api_permit+"/**").permitAll()
            .anyRequest()
            .authenticated();

        http.csrf().disable();
        http.headers().frameOptions().sameOrigin().disable();
        http.cors().configurationSource(new CorsConfigurationSource(){

            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest httpServletRequest) {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.addAllowedOrigin("*");
                configuration.addAllowedHeader( "*");
                configuration.addAllowedMethod( "*");
                return configuration;
            }
        });
    }
}
