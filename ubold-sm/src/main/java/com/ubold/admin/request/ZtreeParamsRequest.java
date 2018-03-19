package com.ubold.admin.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by lenovo on 2017/9/8.
 */
public class ZtreeParamsRequest extends Request {

    @NotBlank
    private String sqlId;
    @NotBlank
    private String idKey;
    @NotBlank
    private String name;
    @NotBlank
    private String pIdKey;
    @NotBlank
    private String scope;
    @NotNull
    private boolean enable;
    //点击id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSqlId() {
        return sqlId;
    }

    public void setSqlId(String sqlId) {
        this.sqlId = sqlId;
    }

    public String getIdKey() {
        return idKey;
    }

    public void setIdKey(String idKey) {
        this.idKey = idKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getpIdKey() {
        return pIdKey;
    }

    public void setpIdKey(String pIdKey) {
        this.pIdKey = pIdKey;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
