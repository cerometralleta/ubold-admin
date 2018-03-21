package com.ubold.admin.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户角色
 * Created by lenovo on 2017/11/11.
 */
@Entity
@Table(name="TB_RBAC_ROLE")
public class Role extends Auditable{

    private String roleName;
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
