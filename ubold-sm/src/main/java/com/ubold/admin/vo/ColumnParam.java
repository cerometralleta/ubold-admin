package com.ubold.admin.vo;

import java.io.Serializable;

/**
 * Created by lenovo on 2017/8/13.
 */
public class ColumnParam implements Serializable{
    private String field;
    private String title;
    private String updateType;
    private boolean isView;
    private boolean isInsert;
    private boolean visible;
    private String dataType;
    private String fieldType;
    private String rule;
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
    private boolean clickToSelect;
//    private String formatter;
//    private String footerFormatter;
//    private String events;
//    private String sorter;
    private String sortName;
//    private String cellStyle;
    private boolean searchable;
    private boolean searchFormatter;
    private boolean escape;

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

    public String getUpdateType() {
        return updateType;
    }

    public void setUpdateType(String updateType) {
        this.updateType = updateType;
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

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
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

    public boolean isRadio() {
        return radio;
    }

    public void setRadio(boolean radio) {
        this.radio = radio;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }

    public String getTitleTooltip() {
        return titleTooltip;
    }

    public void setTitleTooltip(String titleTooltip) {
        this.titleTooltip = titleTooltip;
    }

    public int getRowspan() {
        return rowspan;
    }

    public void setRowspan(int rowspan) {
        this.rowspan = rowspan;
    }

    public int getColspan() {
        return colspan;
    }

    public void setColspan(int colspan) {
        this.colspan = colspan;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public String getHalign() {
        return halign;
    }

    public void setHalign(String halign) {
        this.halign = halign;
    }

    public String getFalign() {
        return falign;
    }

    public void setFalign(String falign) {
        this.falign = falign;
    }

    public String getValign() {
        return valign;
    }

    public void setValign(String valign) {
        this.valign = valign;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public boolean isSortable() {
        return sortable;
    }

    public void setSortable(boolean sortable) {
        this.sortable = sortable;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public boolean isCardVisible() {
        return cardVisible;
    }

    public void setCardVisible(boolean cardVisible) {
        this.cardVisible = cardVisible;
    }

    public boolean isSwitchable() {
        return switchable;
    }

    public void setSwitchable(boolean switchable) {
        this.switchable = switchable;
    }

    public boolean isClickToSelect() {
        return clickToSelect;
    }

    public void setClickToSelect(boolean clickToSelect) {
        this.clickToSelect = clickToSelect;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public boolean isSearchable() {
        return searchable;
    }

    public void setSearchable(boolean searchable) {
        this.searchable = searchable;
    }

    public boolean isSearchFormatter() {
        return searchFormatter;
    }

    public void setSearchFormatter(boolean searchFormatter) {
        this.searchFormatter = searchFormatter;
    }

    public boolean isEscape() {
        return escape;
    }

    public void setEscape(boolean escape) {
        this.escape = escape;
    }
}
