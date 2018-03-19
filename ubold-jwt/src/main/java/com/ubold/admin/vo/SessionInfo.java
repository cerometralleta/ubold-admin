package com.ubold.admin.vo;

import java.io.Serializable;

/**
 * Created by ningzuokun on 2018/3/19.
 */
public class SessionInfo implements Serializable {
    private String password;
    private String username;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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
