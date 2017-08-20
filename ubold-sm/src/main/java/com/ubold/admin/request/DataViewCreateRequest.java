package com.ubold.admin.request;

import com.ubold.admin.vo.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据视图创建请求
 * Created by lenovo on 2017/8/13.
 */
public class DataViewCreateRequest implements Serializable {

    @NotBlank(message = "视图编号不能为空值")
    private String dataViewCode;
    @NotBlank(message = "视图名称不能为空值")
    private String dataViewName;

    @NotBlank(message = "SQLID不能为空值")
    private String sqlId;
    private String remark;

    @NotNull(message = "表格选项不能为空值")
    private OptionsVo options;

    @NotNull(message = "列表字段不能为空值")
    private List<ColumnVo> columns;
    private TreeOptionsVo treeOptions;
    private List<DataFilterVo> dataFilters;
    private List<ButtonVo> buttons;

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

    public List<ColumnVo> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnVo> columns) {
        this.columns = columns;
    }

    public TreeOptionsVo getTreeOptions() {
        return treeOptions;
    }

    public void setTreeOptions(TreeOptionsVo treeOptions) {
        this.treeOptions = treeOptions;
    }

    public List<DataFilterVo> getDataFilters() {
        return dataFilters;
    }

    public void setDataFilters(List<DataFilterVo> dataFilters) {
        this.dataFilters = dataFilters;
    }

    public List<ButtonVo> getButtons() {
        return buttons;
    }

    public void setButtons(List<ButtonVo> buttons) {
        this.buttons = buttons;
    }
}
