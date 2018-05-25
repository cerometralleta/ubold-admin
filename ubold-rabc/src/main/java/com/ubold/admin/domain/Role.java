package com.ubold.admin.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户角色
 * Created by lenovo on 2017/11/11.
 */
@Data
@Entity
@Table(name="TB_RBAC_ROLE")
public class Role extends Auditable{

    private String roleName;
    private String remark;
}
