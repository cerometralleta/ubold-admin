package com.ubold.admin.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by lenovo on 2017/11/11.
 */
@Data
@Entity
@Table(name = "TB_RBAC_RESOURCE")
public class Resource extends ResourceInfo {
}
