package com.ubold.admin.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by lenovo on 2017/11/11.
 */
@Entity
@Table(name="TB_RBAC_PERMISSION")
public class Permission extends Auditable{

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
