package com.ubold.admin.request;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by ningzuokun on 2017/11/8.
 */
public class SqlDefineRequest extends Request {

    private String id;
    @NotBlank(message = "sqlId不能为空")
    private String sqlId;


    @NotBlank(message = "sqlName不能为空")
    private String sqlName;

    @NotBlank(message = "selectSql不能为空")
    private String selectSql;

    @NotBlank(message = "masterTable不能为空")
    private String masterTable;

    /**
     * 主表对应的ID
     */
    @NotBlank(message = "masterTableId不能为空")
    private String masterTableId;

    public String getSqlId() {
        return sqlId;
    }

    public void setSqlId(String sqlId) {
        this.sqlId = sqlId;
    }

    public String getSqlName() {
        return sqlName;
    }

    public void setSqlName(String sqlName) {
        this.sqlName = sqlName;
    }

    public String getSelectSql() {
        return selectSql;
    }

    public void setSelectSql(String selectSql) {
        this.selectSql = selectSql;
    }

    public String getMasterTable() {
        return masterTable;
    }

    public void setMasterTable(String masterTable) {
        this.masterTable = masterTable;
    }

    public String getMasterTableId() {
        return masterTableId;
    }

    public void setMasterTableId(String masterTableId) {
        this.masterTableId = masterTableId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
