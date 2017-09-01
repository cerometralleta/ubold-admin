package com.ubold.admin.vo;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/9/2.
 */
public class ConditionParam implements Serializable{
    private String value;
    private String field;
    private String expression;
    private String title;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
