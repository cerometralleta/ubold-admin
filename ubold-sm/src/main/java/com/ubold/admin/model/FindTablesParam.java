package com.ubold.admin.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by lenovo on 2018/2/12.
 */
@Data
public class FindTablesParam {

    @ApiModelProperty(value = "表名")
    @NotBlank
    private String tablename;

//    @NotBlank
    @ApiModelProperty(value = "数据源")
    private String  datasource;
}
