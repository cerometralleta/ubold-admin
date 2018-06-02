package com.ubold.admin.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by lenovo on 2017/8/13.
 */
@Data
@Entity
@Table(name="t_sm_dataview")
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
}
