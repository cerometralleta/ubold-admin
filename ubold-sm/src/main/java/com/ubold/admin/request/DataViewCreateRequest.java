package com.ubold.admin.request;

import com.ubold.admin.vo.ButtonParam;
import com.ubold.admin.vo.ColumnParam;
import com.ubold.admin.vo.DataFilterParam;
import com.ubold.admin.vo.OptionsParam;
import com.ubold.admin.vo.TreeOptionsParam;

import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * 数据视图创建请求
 * Created by lenovo on 2017/8/13.
 */
public class DataViewCreateRequest extends Request {

    @NotBlank(message = "视图编号不能为空值")
    private String dataViewCode;
    @NotBlank(message = "视图名称不能为空值")
    private String dataViewName;

    @NotBlank(message = "SQLID不能为空值")
    private String sqlId;
    private String remark;
    private String id;

    @NotNull(message = "表格选项不能为空值")
    private OptionsParam options;

    @NotNull(message = "列表字段不能为空值")
    private List<ColumnParam> columns;
    private TreeOptionsParam treeOptions;
    private List<DataFilterParam> dataFilters;
    private List<ButtonParam> buttons;
    private long version;

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public OptionsParam getOptions() {
        return options;
    }

    public void setOptions(OptionsParam options) {
        this.options = options;
    }

    public List<ColumnParam> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnParam> columns) {
        this.columns = columns;
    }

    public TreeOptionsParam getTreeOptions() {
        return treeOptions;
    }

    public void setTreeOptions(TreeOptionsParam treeOptions) {
        this.treeOptions = treeOptions;
    }

    public List<DataFilterParam> getDataFilters() {
        return dataFilters;
    }

    public void setDataFilters(List<DataFilterParam> dataFilters) {
        this.dataFilters = dataFilters;
    }

    public List<ButtonParam> getButtons() {
        return buttons;
    }

    public void setButtons(List<ButtonParam> buttons) {
        this.buttons = buttons;
    }
}
