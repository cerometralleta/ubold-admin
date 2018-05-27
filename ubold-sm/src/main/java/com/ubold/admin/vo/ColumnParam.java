package com.ubold.admin.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/8/13.
 */
@Data
public class ColumnParam implements Serializable{
    private String field;
    private String title;
    private String updateType;
    private boolean view;
    private boolean insert;
    private boolean visible;
    private String dataType;
    private String fieldType;
    private int maxlength;
    private int idx;
    private boolean radio;
    private boolean checkbox;
    private String titleTooltip;
//    private String class;
    private int rowspan;
    private int colspan;
    private String align;
    private String halign;
    private String falign;
    private String valign;
    private String width;
    private boolean sortable;
    private String order;
    private boolean cardVisible;
    private boolean switchable;
    private boolean unduplicated;
    private boolean clickToSelect;
    private String formatter;
//    private String footerFormatter;
//    private String events;
//    private String sorter;
    private String sortName;
//    private String cellStyle;
    private boolean searchable;
    private boolean searchFormatter;
    private boolean escape;
    private String pattern;
    private String dataFormat;
}
