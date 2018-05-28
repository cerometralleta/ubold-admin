package com.ubold.admin.config;

import com.ubold.admin.cors.UboldCorsConfigurationSource;
import com.ubold.admin.filter.JWTAuthenticationFilter;
import com.ubold.admin.filter.JWTLoginFilter;
import com.ubold.admin.provider.CustomAuthenticationProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ningzuokun on 2017/12/18.
 */
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final List<String> excludeList = new ArrayList<>();
    static {
        excludeList.add("/login");
        excludeList.add("/druid/**");
        excludeList.add("/swagger-ui.html");
        excludeList.add("/swagger-resources/**");
        excludeList.add("/swagger-resources/**");
        excludeList.add("/webjars/**");
        excludeList.add("/v2/api-docs/**");
    }


    // 设置 HTTP 验证规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 关闭csrf验证
        http.csrf().disable()

                // 对请求进行认证
                .authorizeRequests()

                // 所有 / 的所有请求 都放行
                .antMatchers("/").permitAll()

                // 所有放行的链接
                .antMatchers(excludeList.toArray(new String[excludeList.size()])).permitAll()

                // 所有请求需要身份认证
                .anyRequest().authenticated()
                .and()
                // 添加一个过滤器 所有访问 /login 的请求交给 JWTLoginFilter 来处理 这个类处理所有的JWT相关内容
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),UsernamePasswordAuthenticationFilter.class)
                // 添加一个过滤器验证其他请求的Token是否合法
                .addFilterBefore(new JWTAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);

        http.csrf().disable();
        http.headers().frameOptions().sameOrigin().disable();
        http.cors().configurationSource(new UboldCorsConfigurationSource());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用自定义身份验证组件
        auth.authenticationProvider(new CustomAuthenticationProvider());
    }

    //添加排除拦截的url
    public static void addExcludeUrl(String url){
        excludeList.add(url);
    }
}
