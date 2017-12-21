package com.ubold.admin.vo;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by lenovo on 2017/11/11.
 */
public class LoginParam extends JwtRequestParam {

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
