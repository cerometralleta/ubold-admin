package com.ubold.admin.vo;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by lenovo on 2018/2/12.
 */
public class QuerytableParam {

    @NotBlank
    private String tablename;

    //数据源ip
//    @NotBlank
    private String  datasource;
//    @NotBlank
    private String  tableschema;

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }

    public String getTableschema() {
        return tableschema;
    }

    public void setTableschema(String tableschema) {
        this.tableschema = tableschema;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }
}
