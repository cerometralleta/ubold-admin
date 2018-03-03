package com.ubold.admin.vo;

import java.io.Serializable;

/**
 * Created by lenovo on 2018/2/12.
 */
public class TablesResult implements Serializable{

    private String tableName;
    private String remark;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
