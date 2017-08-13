package com.ubold.admin.domain;

import com.ubold.admin.vo.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;

/**
 * Created by lenovo on 2017/8/13.
 */
@Entity
@Table(name="TB_SM_VIEW")
public class DataView extends Auditable{
    private String dataViewCode;
    private String dataViewName;
    private String sqlId;
    private String remark;
    private OptionsVo options;
    private ArrayList<ColumnVo> columns;
    private TreeOptionsVo treeOptions;
    private DataFilterVo dataFilters;
    private ArrayList<ButtonVo> buttons;

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

    public OptionsVo getOptions() {
        return options;
    }

    public void setOptions(OptionsVo options) {
        this.options = options;
    }

    public ArrayList<ColumnVo> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<ColumnVo> columns) {
        this.columns = columns;
    }

    public TreeOptionsVo getTreeOptions() {
        return treeOptions;
    }

    public void setTreeOptions(TreeOptionsVo treeOptions) {
        this.treeOptions = treeOptions;
    }

    public DataFilterVo getDataFilters() {
        return dataFilters;
    }

    public void setDataFilters(DataFilterVo dataFilters) {
        this.dataFilters = dataFilters;
    }

    public ArrayList<ButtonVo> getButtons() {
        return buttons;
    }

    public void setButtons(ArrayList<ButtonVo> buttons) {
        this.buttons = buttons;
    }
}
