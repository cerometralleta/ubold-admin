package com.ubold.admin.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by lenovo on 2017/11/10.
 */
@Entity
@Table(name="TB_USER_INFO")
public class User extends Auditable{

    private String password;
    private String username;

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
