package com.ubold.admin.domain;

import lombok.Data;

/**
 * Created by lenovo on 2017/11/10.
 */
@Data
public class User extends Auditable{
    private String password;
    private String username;
}
