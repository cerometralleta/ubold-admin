package com.ubold.admin.request;

import com.ubold.admin.vo.FieldVo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2017/8/28.
 */
public class FormViewRequest implements Serializable{
    private String id;
    private String code;
    private String sqlId;
    private String url;
    private String remark;
    private long version;

    @NotNull(message = "字段不能为空")
    private List<FieldVo> columns;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSqlId() {
        return sqlId;
    }

    public void setSqlId(String sqlId) {
        this.sqlId = sqlId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<FieldVo> getColumns() {
        return columns;
    }

    public void setColumns(List<FieldVo> columns) {
        this.columns = columns;
    }
}
