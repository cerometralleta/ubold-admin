package com.ubold.admin.vo;

import com.ubold.admin.request.Request;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by ningzuokun on 2018/3/22.
 */
@Data
public class AuthorizeUrlParam extends Request {

    @ApiModelProperty(value="鉴权URL",name="url",required=true)
    private String url;
}
