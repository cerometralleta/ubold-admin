package com.ubold.admin.vo;

import java.io.Serializable;

/**
 * Created by lenovo on 2018/4/15.
 */
public class SQLColumnschemaResult implements Serializable{
    private String columnName;
    private String columnKey;
    private String columnComment;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }
}
