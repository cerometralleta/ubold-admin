package com.ubold.admin.vo;

import com.ubold.admin.domain.Resource;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 授权结果
 * Created by ningzuokun on 2017/12/21.
 */
@Data
public class GetMenuResult implements Serializable {

    private List<Resource> resources;
}
