package com.ubold.admin.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 用户直接和权限关联
 * Created by lenovo on 2017/11/11.
 */
@Data
@Entity
@Table(name = "TB_RBAC_GROUP_USER_RELATION")
public class GroupUserRelation extends Auditable {

    private String userId;
    private String groupId;
}
