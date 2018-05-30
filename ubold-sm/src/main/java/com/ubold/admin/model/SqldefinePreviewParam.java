package com.ubold.admin.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class SqldefinePreviewParam {

    @ApiModelProperty(value = "表名")
    @NotBlank
    private String tablename;

    //    @NotBlank
    @ApiModelProperty(value = "数据源")
    private String  datasource;
}
