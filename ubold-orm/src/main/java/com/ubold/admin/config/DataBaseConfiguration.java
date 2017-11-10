package com.ubold.admin.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by ningzuokun on 2017/11/10.
 */
@Configuration
public class DataBaseConfiguration {

    @Primary
    @Bean
    @ConfigurationProperties(prefix="spring.datasource")//加载所有以spring.datasource开头的参数来初始化连接池
    public DataSource dataSource() {
        return DruidDataSourceBuilder.create().build();
    }
}
