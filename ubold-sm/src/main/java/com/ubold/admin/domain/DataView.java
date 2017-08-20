package com.ubold.admin.domain;

import com.ubold.admin.vo.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;

/**
 * Created by lenovo on 2017/8/13.
 */
@Entity
@Table(name="TB_SM_DATAVIEW")
public class DataView extends Auditable{

    @Column(name = "dataviewcode")
    private String dataViewCode;

    @Column(name = "dataviewname")
    private String dataViewName;

    @Column(name = "sqlid")
    private String sqlId;
    private String remark;
    private String options;
    private String columns;

    @Column(name = "treeoptions")
    private String treeOptions;

    @Column(name = "datafilters")
    private String dataFilters;
    private String buttons;

    public String getDataViewCode() {
        return dataViewCode;
    }

    public void setDataViewCode(String dataViewCode) {
        this.dataViewCode = dataViewCode;
    }

    public String getDataViewName() {
        return dataViewName;
    }

    public void setDataViewName(String dataViewName) {
        this.dataViewName = dataViewName;
    }

    public String getSqlId() {
        return sqlId;
    }

    public void setSqlId(String sqlId) {
        this.sqlId = sqlId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public String getTreeOptions() {
        return treeOptions;
    }

    public void setTreeOptions(String treeOptions) {
        this.treeOptions = treeOptions;
    }

    public String getDataFilters() {
        return dataFilters;
    }

    public void setDataFilters(String dataFilters) {
        this.dataFilters = dataFilters;
    }

    public String getButtons() {
        return buttons;
    }

    public void setButtons(String buttons) {
        this.buttons = buttons;
    }
}
