package com.ubold.admin.model;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/11/11.
 */
public class LoginParam implements Serializable{

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "用户密码不能为空")
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
