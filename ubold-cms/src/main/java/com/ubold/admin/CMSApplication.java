package com.ubold.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Created by ningzuokun on 2017/5/15.
 */
@SpringBootApplication
@EnableCaching
public class CMSApplication {

    public static void main(String[] args) {
        SpringApplication.run(CMSApplication.class, args);
    }
}
