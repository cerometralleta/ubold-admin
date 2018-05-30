package com.ubold.admin.model;

import com.ubold.admin.domain.Resources;
import com.ubold.admin.domain.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2018/5/30.
 */
@Data
public class AccountInfoModel implements Serializable {

    private AccountInfoModel.App app;
    private User user;
    private List<Resources> menu;

    //APP系统配置
    @Data
    public static class App implements Serializable{

        @ApiModelProperty(value = "应用名称")
        private String name;

        @ApiModelProperty(value = "应用概要")
        private String description;
    }
}
