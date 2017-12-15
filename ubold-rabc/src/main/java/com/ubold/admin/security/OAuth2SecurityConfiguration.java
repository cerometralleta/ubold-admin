package com.ubold.admin.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zkning on 2017/01/01.
 */
@Configuration
public class OAuth2SecurityConfiguration extends WebSecurityConfigurerAdapter {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().permitAll();
//                .loginPage("http://localhost:4200/#/login");
//                  .successHandler(new AuthenticationSuccessHandler(){
//
//                            @Override
//                            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
//                                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
//                            }
//                        })
//                .successForwardUrl("http://localhost:4200/#/home")
//                .permitAll();

        //允许基于使用HttpServletRequest限制访问
        http.authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()   //对preflight放行
                .antMatchers("/", "/login**","/user", "/rabc/**").permitAll()
                .anyRequest().authenticated().and().httpBasic();

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
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { // @formatter:off
        auth.parentAuthenticationManager(authenticationManager)
                .inMemoryAuthentication()
                .withUser("john")
                .password("123")
                .roles("USER");
    } // @formatter:on

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/druid/**");
    }


}