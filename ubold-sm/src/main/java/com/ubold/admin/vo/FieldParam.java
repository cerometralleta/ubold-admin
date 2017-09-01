package com.ubold.admin.vo;

import java.io.Serializable;
import java.util.List;

/**
 *自定义视图字段
 * Created by lenovo on 2017/8/28.
 */
public class FieldParam implements Serializable{
    private String field;
    private String title;
    private String updateTyp;
    private boolean isView;
    private boolean isInsert;
    private boolean visible;
    private String dataType;
    private String fieldType;
    private List rule;
    private int maxlength;
    private int idx;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdateTyp() {
        return updateTyp;
    }

    public void setUpdateTyp(String updateTyp) {
        this.updateTyp = updateTyp;
    }

    public boolean isView() {
        return isView;
    }

    public void setView(boolean view) {
        isView = view;
    }

    public boolean isInsert() {
        return isInsert;
    }

    public void setInsert(boolean insert) {
        isInsert = insert;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public List getRule() {
        return rule;
    }

    public void setRule(List rule) {
        this.rule = rule;
    }

    public int getMaxlength() {
        return maxlength;
    }

    public void setMaxlength(int maxlength) {
        this.maxlength = maxlength;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }
}
