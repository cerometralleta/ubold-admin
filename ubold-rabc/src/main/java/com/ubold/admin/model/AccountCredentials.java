package com.ubold.admin.model;

import com.ubold.admin.domain.Resources;
import com.ubold.admin.domain.UserInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ningzuokun on 2018/3/26.
 */
@Data
public class AccountCredentials implements Serializable {

    private App app;
    private UserInfo user;
    private List<Resources> menu;
    private String token;

    @Data
    public static class App implements Serializable{

        @ApiModelProperty(value = "应用名称")
        private String name;

        @ApiModelProperty(value = "应用概要")
        private String description;
    }
}


