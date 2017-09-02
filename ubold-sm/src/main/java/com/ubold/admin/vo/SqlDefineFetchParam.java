package com.ubold.admin.vo;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/9/2.
 */
public class SqlDefineFetchParam implements Serializable{

    @NotBlank
    private String sqlId;

    @NotBlank
    private String id;

    public String getSqlId() {
        return sqlId;
    }

    public void setSqlId(String sqlId) {
        this.sqlId = sqlId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
