package com.ubold.admin.config;

import com.ubold.admin.filter.AuthenticationTokenProcessingFilter;
import com.ubold.admin.filter.ServiceUnauthorizedEntryPoint;
import com.ubold.admin.service.impl.UboldAccessDecisionManager;
import com.ubold.admin.service.impl.UboldAuthenticationProvider;
import com.ubold.admin.service.impl.UboldUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by Kim on 2015/9/14.
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UboldUserDetailsService uboldUserDetailsService;

    @Autowired
    UboldAccessDecisionManager uboldAccessDecisionManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().sameOrigin().disable()
                .authorizeRequests()
                .antMatchers("/api/permit/**").permitAll()
                .accessDecisionManager(uboldAccessDecisionManager)
                .anyRequest().fullyAuthenticated() //其他url需要鉴权
        .and().formLogin()
                .loginProcessingUrl("/login").permitAll()
        .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .and().csrf().disable() //disable csrf
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)//无状态的web调用的stateless authentication
        .and()
                .addFilterBefore(new AuthenticationTokenProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(new ServiceUnauthorizedEntryPoint());
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
        auth.userDetailsService(uboldUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
        UboldAuthenticationProvider uboldAuthenticationProvider = new UboldAuthenticationProvider();
        uboldAuthenticationProvider.setUserDetailsService(uboldUserDetailsService);
        auth.authenticationProvider(uboldAuthenticationProvider);
    }
}