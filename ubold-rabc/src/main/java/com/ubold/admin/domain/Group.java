package com.ubold.admin.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户组
 * Created by lenovo on 2017/11/11.
 */
@Data
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
}
