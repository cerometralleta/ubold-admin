package com.ubold.admin.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Transient;
import java.util.List;

/**
 * Created by lenovo on 2017/11/11.
 */
@Data
public class ResourceInfo extends Auditable {

    @ApiModelProperty(value = "资源名称")
    private String name;

    @ApiModelProperty(value = "资源备注")
    private String remark;

    @ApiModelProperty(value = "所属资源")
    private String parent;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "链接")
    private String link;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "附属资源")
    @Transient
    private List<ResourceInfo> childs;
}
