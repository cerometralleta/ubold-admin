package com.ubold.admin.model;

/**
 * Created by lenovo on 2017/8/13.
 */
public class ButtonParam {
    private String id;
    private String option;//0:接口,1:弹窗,2:新窗口
    private String modal;
    private String size;//窗口大小
    private String icon;
    private String title;
    private String url;
    private String position;//导航按钮 1:导航按钮,0:行内按钮
    private String sort;
    private String btnsize;
    private String color;

    public String getBtnsize() {
        return btnsize;
    }

    public void setBtnsize(String btnsize) {
        this.btnsize = btnsize;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModal() {
        return modal;
    }

    public void setModal(String modal) {
        this.modal = modal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
