package com.ubold.admin.config;

import com.alibaba.fastjson.JSONObject;
import com.ubold.admin.filter.ServiceUnauthorizedEntryPoint;
import com.ubold.admin.filter.UboldUsernamePasswordAuthenticationFilter;
import com.ubold.admin.response.Response;
import com.ubold.admin.service.impl.UboldAccessDecisionManager;
import com.ubold.admin.service.impl.UboldAuthenticationProvider;
import com.ubold.admin.service.impl.UboldUserDetailsService;
import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        http.headers().frameOptions().sameOrigin().disable()
                .authorizeRequests()
                .antMatchers("/api/permit/**").permitAll()
                .accessDecisionManager(uboldAccessDecisionManager)
                .anyRequest().fullyAuthenticated() //其他url需要鉴权
        .and().formLogin()
                .loginProcessingUrl("/login").permitAll()
                .failureHandler(new AuthenticationFailureHandler(){

                    @Override
                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                        httpServletResponse.setContentType(ContentType.APPLICATION_JSON.toString());
                        httpServletResponse.getWriter().print(JSONObject.toJSONString(Response.FAILURE()));
                    }
                })
                .successHandler(new AuthenticationSuccessHandler(){

                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        httpServletResponse.setContentType(ContentType.APPLICATION_JSON.toString());
                        httpServletResponse.getWriter().print(JSONObject.toJSONString(Response.SUCCESS()));
                    }
                })
        .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .and().csrf().disable() //disable csrf
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)//无状态的web调用的stateless authentication
        .and()
                .addFilterAt(this.uboldUsernamePasswordAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(new ServiceUnauthorizedEntryPoint());
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
        UboldAuthenticationProvider uboldAuthenticationProvider = new UboldAuthenticationProvider();
        uboldAuthenticationProvider.setUserDetailsService(uboldUserDetailsService);
        uboldAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        auth.authenticationProvider(uboldAuthenticationProvider);
    }

    @Bean
    public UboldUsernamePasswordAuthenticationFilter uboldUsernamePasswordAuthenticationFilter() throws Exception {
        UboldUsernamePasswordAuthenticationFilter filter = new UboldUsernamePasswordAuthenticationFilter();
        filter.setAuthenticationManager(this.authenticationManager());
        return filter;
    }

//    @Bean
//    @Autowired
//    public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore){
//        TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
//        handler.setTokenStore(tokenStore);
//        handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
//        handler.setClientDetailsService(clientDetailsService);
//        return handler;
//    }
//
//    @Bean
//    @Autowired
//    public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
//        TokenApprovalStore store = new TokenApprovalStore();
//        store.setTokenStore(tokenStore);
//        return store;
//    }
}