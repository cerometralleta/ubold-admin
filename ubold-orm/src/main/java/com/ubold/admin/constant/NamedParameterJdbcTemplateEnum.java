package com.ubold.admin.constant;

/**
 * Created by ningzuokun on 2018/2/27.
 */
public enum NamedParameterJdbcTemplateEnum {
    MASTER(DataSourceCodeConstant.MASTER, "主数据源");

    private int dataSourceCode;
    private String dataSourceName;

    private NamedParameterJdbcTemplateEnum(int dataSourceCode, String dataSourceName) {
        this.dataSourceCode = dataSourceCode;
        this.dataSourceName = dataSourceName;
    }

    public int getDataSourceCode() {
        return dataSourceCode;
    }

    public void setDataSourceCode(int dataSourceCode) {
        this.dataSourceCode = dataSourceCode;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public static class DataSourceCodeConstant {
        public static final int MASTER = 1;
    }
}
