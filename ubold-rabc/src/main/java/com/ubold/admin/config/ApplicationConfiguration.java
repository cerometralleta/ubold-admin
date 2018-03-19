package com.ubold.admin.config;

import com.ubold.admin.filter.UboldAuthorityFilter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * Created by ningzuokun on 2018/3/14.
 */
@Configuration
public class ApplicationConfiguration {

    @Bean(name = "uboldAuthorityFilter")
    public Filter uboldAuthorityFilter() {
        return new UboldAuthorityFilter();
    }

    @Bean
    public FilterRegistrationBean customFilterRegister() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(uboldAuthorityFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
