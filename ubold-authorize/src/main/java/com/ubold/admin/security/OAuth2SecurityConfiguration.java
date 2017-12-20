package com.ubold.admin.security;

import com.ubold.admin.constant.PermitPrefixURI;
import com.ubold.admin.cors.UboldCorsConfigurationSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsUtils;

/**
 * Created by zkning on 2017/01/01.
 */
@Configuration
public class OAuth2SecurityConfiguration extends WebSecurityConfigurerAdapter {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    .requestMatchers()
                 .requestMatchers(CorsUtils::isPreFlightRequest) //对preflight放行
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"/**/"+ PermitPrefixURI.api_permit+"/**").permitAll()
                .antMatchers("/oauth/token").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll();

        http.csrf().disable();
        http.headers().frameOptions().sameOrigin().disable();
        http.cors().configurationSource(new UboldCorsConfigurationSource());
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
//                .parentAuthenticationManager(authenticationManagerBean)
                .inMemoryAuthentication()
                .withUser("john1").password("123").authorities("USER");
    }

    /**
     * 这一步的配置是必不可少的，否则SpringBoot会自动配置一个AuthenticationManager,覆盖掉内存中的用户
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager manager = super.authenticationManagerBean();
        return manager;
    }

    /**
     * 初始化用户信息,同authenticationManagerBean
     * @return
     */
//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("user_1").password("123456").authorities("USER").build());
//        manager.createUser(User.withUsername("user_2").password("123456").authorities("USER").build());
//        return manager;
//    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/druid/**");
    }
}