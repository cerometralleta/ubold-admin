package com.ubold.admin.constant;

/**
 * Created by ningzuokun on 2018/2/27.
 */
public enum NamedParameterJdbcTemplateEnum {
    MASTER(DataSourceCodeConstant.MASTER, "主数据源");

    private String dataSourceCode;
    private String dataSourceName;

    private NamedParameterJdbcTemplateEnum(String dataSourceCode, String dataSourceName) {
        this.dataSourceCode = dataSourceCode;
        this.dataSourceName = dataSourceName;
    }

    public String getDataSourceCode() {
        return dataSourceCode;
    }

    public void setDataSourceCode(String dataSourceCode) {
        this.dataSourceCode = dataSourceCode;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public static class DataSourceCodeConstant {
        public static final String MASTER = "127.0.0.1";
    }
}
