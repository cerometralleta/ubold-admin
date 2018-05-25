package com.ubold.admin.domain;

import lombok.Data;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by lenovo on 2017/11/11.
 */
@Data
@Entity
@Table(name = "TB_RBAC_RESOURCE")
public class Resource extends Auditable {

    private String name;
    private String remark;
    private String parent;
    private Integer type;
    private String link;
    private String icon;

    @Transient
    private List<Resource> childs;

    public List<Resource> getChilds() {
        return childs;
    }
}
