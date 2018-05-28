package com.ubold.admin.model;

import java.io.Serializable;

/**
 * 存储用户名密码，另一个是一个权限类型，负责存储权限和角色。
 * Created by ningzuokun on 2017/12/18.
 */
public class AccountCredentials implements Serializable{

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

