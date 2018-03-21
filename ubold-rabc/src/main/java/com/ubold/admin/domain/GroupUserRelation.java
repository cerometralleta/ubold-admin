package com.ubold.admin.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户直接和权限关联
 * Created by lenovo on 2017/11/11.
 */
@Entity
@Table(name = "TB_RBAC_GROUP_USER_RELATION")
public class GroupUserRelation extends Auditable {

    private String userId;
    private String groupId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
