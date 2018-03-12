package com.ubold.admin.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2017/9/2.
 */
public class BootstrapSearchParam implements Serializable{

    private Integer pageSize = 50;
    private Integer pageNumber = 1;
    private String searchText;
    private String sortName;
    private String sortOrder;
    private TreeOptionsParam treeOptions;
    private List<ConditionParam> searchArray;

    public TreeOptionsParam getTreeOptions() {
        return treeOptions;
    }

    public void setTreeOptions(TreeOptionsParam treeOptions) {
        this.treeOptions = treeOptions;
    }

    public List<ConditionParam> getSearchArray() {
        return searchArray;
    }

    public void setSearchArray(List<ConditionParam> searchArray) {
        this.searchArray = searchArray;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
}
