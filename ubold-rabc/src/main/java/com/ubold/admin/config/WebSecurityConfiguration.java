package com.ubold.admin.config;

import com.ubold.admin.constant.PermitPrefixURI;
import com.ubold.admin.filter.ServiceUnauthorizedEntryPoint;
import com.ubold.admin.security.UboldAccessDecisionManager;
import com.ubold.admin.security.UboldAuthenticationProvider;
import com.ubold.admin.security.UboldUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Kim on 2015/9/14.
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    UboldUserDetailsService uboldUserDetailsService;

    @Autowired
    UboldAccessDecisionManager uboldAccessDecisionManager;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(PermitPrefixURI.api_permit +"/**").permitAll()
                .accessDecisionManager(uboldAccessDecisionManager)
                .anyRequest().fullyAuthenticated()//其他url需要鉴权
        .and().csrf().disable() //disable csrf
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)//无状态的web调用的stateless authentication
        .and()
//                .addFilterAt(this.uboldUsernamePasswordAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(new ServiceUnauthorizedEntryPoint())
        .and().headers().cacheControl()//禁用缓存
        .and().frameOptions().sameOrigin().disable();
    }

    @Autowired
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        UboldAuthenticationProvider uboldAuthenticationProvider = new UboldAuthenticationProvider();
        uboldAuthenticationProvider.setUserDetailsService(uboldUserDetailsService);
        uboldAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        auth.authenticationProvider(uboldAuthenticationProvider);
    }
}