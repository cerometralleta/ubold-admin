package com.ubold.admin.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * Created by lenovo on 2017/11/10.
 */
@Data
@MappedSuperclass
public class User extends Auditable{

    @JsonIgnore
    private String password;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "用户照片")
    private String avatar;
}
