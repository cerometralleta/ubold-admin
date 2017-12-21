package com.ubold.admin.vo;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * jwt 基础header参数
 * Created by ningzuokun on 2017/12/21.
 */
public class JwtheaderParam implements Serializable{

    /**
     * 授权token
     */
    @NotBlank(message = "authorization not blank")
    private String authorization;

    /**
     * username
     */
    private String username;

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
