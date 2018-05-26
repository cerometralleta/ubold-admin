package com.ubold.admin.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by lenovo on 2017/11/11.
 */
@Data
@Entity
@Table(name = "TB_RBAC_RESOURCE")
public class Resource extends Auditable {

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
    private List<Resource> childs;
}
