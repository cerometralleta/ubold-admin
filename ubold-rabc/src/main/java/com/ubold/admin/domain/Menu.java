package com.ubold.admin.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by lenovo on 2017/11/11.
 */
@Entity
@Table(name="TB_RBAC_MENU")
public class Menu extends Auditable{

    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
