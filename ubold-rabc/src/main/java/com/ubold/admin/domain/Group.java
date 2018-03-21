package com.ubold.admin.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户组
 * Created by lenovo on 2017/11/11.
 */
@Entity
@Table(name = "TB_RBAC_GROUP")
public class Group extends Auditable {

    /**
     * 用户分组
     */
    private String name;
    private String remark;

    /**
     * 上级
     */
    private String parent;

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
}
