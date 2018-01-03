package com.ubold.admin;

import com.ubold.admin.constant.PermitPrefixURI;
import com.ubold.admin.cors.UboldCorsConfigurationSource;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableOAuth2Sso
@Configuration
@Order(1)
public class ClientSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**")
            .authorizeRequests()
            .antMatchers("/**/"+ PermitPrefixURI.permit+"/**").permitAll()
            .anyRequest()
            .authenticated();

        http.csrf().disable();
        http.headers().frameOptions().sameOrigin().disable();
        http.cors().configurationSource(new UboldCorsConfigurationSource());
    }
}
