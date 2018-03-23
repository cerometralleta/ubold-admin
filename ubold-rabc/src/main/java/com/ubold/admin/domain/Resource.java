package com.ubold.admin.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by lenovo on 2017/11/11.
 */
@Entity
@Table(name = "TB_RBAC_RESOURCE")
public class Resource extends Auditable {

    private String name;
    private String remark;
    private String parent;
    private String code;
    private Integer type;
    private String link;
    private String icon;

    @Transient
    private List<Resource> childs;

    public List<Resource> getChilds() {
        return childs;
    }

    public void setChilds(List<Resource> childs) {
        this.childs = childs;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
