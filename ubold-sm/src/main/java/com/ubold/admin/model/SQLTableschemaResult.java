package com.ubold.admin.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2018/2/12.
 */
public class SQLTableschemaResult implements Serializable{

    private String tableName;
    private String tableComment;
    private List<SQLColumnschemaResult> sqlColumnschemaResultList;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public List<SQLColumnschemaResult> getSqlColumnschemaResultList() {
        return sqlColumnschemaResultList;
    }

    public void setSqlColumnschemaResultList(List<SQLColumnschemaResult> sqlColumnschemaResultList) {
        this.sqlColumnschemaResultList = sqlColumnschemaResultList;
    }
}
