package com.ubold.admin.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户角色关联
 * Created by lenovo on 2017/11/11.
 */
@Entity
@Table(name = "TB_RBAC_ROLE_RESOURCE_RELATION")
public class RoleResourceRelation extends Auditable {

    private String userId;
    private String resourceId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
}
