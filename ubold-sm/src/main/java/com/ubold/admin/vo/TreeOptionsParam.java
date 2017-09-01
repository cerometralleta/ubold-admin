package com.ubold.admin.vo;

/**
 * Created by lenovo on 2017/8/13.
 */
public class TreeOptionsParam {
    private boolean isShow;
    private String sqlId;
    private String idKey;
    private String name;
    private String pIdKey;
    private String scope;
    private String width;

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
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

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getRelationField() {
        return relationField;
    }

    public void setRelationField(String relationField) {
        this.relationField = relationField;
    }

    private String relationField
            ;
}
