package com.ubold.admin.domain;

import com.ubold.admin.vo.FieldVo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by lenovo on 2017/8/28.
 */
@Entity
@Table(name="TB_SM_FORMVIEW")
public class FormView extends Auditable{
    private String code;

    @Column(name = "sqlid")
    private String sqlId;
    private String url;
    private String remark;
    private String columns;

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

    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }
}
