package com.ubold.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * Created by ningzuokun on 2017/12/18.
 */
@SpringBootApplication
@EnableResourceServer
public class RABCApplication {

    public static void main(String[] args) {
        SpringApplication.run(RABCApplication.class, args);
    }
}
