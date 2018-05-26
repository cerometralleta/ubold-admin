package com.ubold.admin.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by lenovo on 2018/2/12.
 */
@Data
public class QuerytableParam {

    @ApiModelProperty(value = "表名")
    @NotBlank
    private String tablename;

//    @NotBlank
    @ApiModelProperty(value = "数据源")
    private String  datasource;

//    @NotBlank
    @ApiModelProperty(value = "数据库概要")
    private String  tableschema;
}
