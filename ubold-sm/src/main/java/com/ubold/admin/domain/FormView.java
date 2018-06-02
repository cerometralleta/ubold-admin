package com.ubold.admin.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by lenovo on 2017/8/28.
 */
@Data
@Entity
@Table(name="t_sm_formview")
public class FormView extends Auditable{
    private String code;

    @Column(name = "sqlid")
    private String sqlId;
    private String url;
    private String remark;
    private String columns;
}
