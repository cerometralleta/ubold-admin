package com.ubold.admin.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zkning on 2017/8/13.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OptionsParam implements Serializable{
    private String classes = "table table-hover";
    private String sortClass;
    private int height;
    private String undefinedText;
    //条纹
    private boolean striped;
//    private String sortName;
    private String sortOrder;
    private boolean  sortStable;
    private String iconsPrefix;
    private String iconSize;
    private String buttonsClass;
//    private String icons;
    private List data = new ArrayList();
    private String dataField;
    private String totalField;
    private String  ajax;
    private String  method;
    private String url;
    private boolean cache;
    private String contentType;
    private String dataType;
    private String ajaxOptions;
//    private String queryParams;
    private String queryParamsType;
//    private String responseHandler;
    private boolean pagination;
    private boolean paginationLoop;
    private boolean onlyInfoPagination;
    private String sidePagination;
    private String pageNumber;
    private String pageSize;
//    private String pageList;
    private String selectItemName;
    private boolean smartDisplay;
    private boolean escape;
    private boolean search;
    private boolean searchOnEnterKey;
    private boolean strictSearch;
    private String searchText;
    private int searchTimeOut;
    private int trimOnSearch;
    private boolean showHeader;
    private boolean showFooter;
    private boolean showColumns;
    private boolean showRefresh;
    private boolean showToggle;
    private boolean showPaginationSwitch;
    private int minimumCountColumns;
    private String idField;
    private boolean editView;
    private String uniqueId;
    private boolean cardView;
    private boolean detailView;
//    private String detailFormatter;
    private String searchAlign;
    private String buttonsAlign;
    private String toolbarAlign;
    private String paginationVAlign;
    private String paginationHAlign;
    private String paginationDetailHAlign;
    private String paginationPreText;
    private String paginationNextText;
    private boolean clickToSelect;
    private boolean singleSelect;
    private String toolbar;
    private boolean checkboxHeader;
    private boolean maintainSelected;
    private boolean sortable;
    private boolean silentSort;
//    private String rowStyle;
//    private String rowAttributes;
//    private String customSearch;
//    private String customSort;
    private String locale;
//    private String footerStyle;
    private boolean showExport;
    private String exportDataType;
    private String version;
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getExportDataType() {
        return exportDataType;
    }

    public void setExportDataType(String exportDataType) {
        this.exportDataType = exportDataType;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getSortClass() {
        return sortClass;
    }

    public void setSortClass(String sortClass) {
        this.sortClass = sortClass;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getUndefinedText() {
        return undefinedText;
    }

    public void setUndefinedText(String undefinedText) {
        this.undefinedText = undefinedText;
    }

    public boolean isStriped() {
        return striped;
    }

    public void setStriped(boolean striped) {
        this.striped = striped;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public boolean isSortStable() {
        return sortStable;
    }

    public void setSortStable(boolean sortStable) {
        this.sortStable = sortStable;
    }

    public String getIconsPrefix() {
        return iconsPrefix;
    }

    public void setIconsPrefix(String iconsPrefix) {
        this.iconsPrefix = iconsPrefix;
    }

    public String getIconSize() {
        return iconSize;
    }

    public void setIconSize(String iconSize) {
        this.iconSize = iconSize;
    }

    public String getButtonsClass() {
        return buttonsClass;
    }

    public void setButtonsClass(String buttonsClass) {
        this.buttonsClass = buttonsClass;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public String getDataField() {
        return dataField;
    }

    public void setDataField(String dataField) {
        this.dataField = dataField;
    }

    public String getTotalField() {
        return totalField;
    }

    public void setTotalField(String totalField) {
        this.totalField = totalField;
    }

    public String getAjax() {
        return ajax;
    }

    public void setAjax(String ajax) {
        this.ajax = ajax;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isCache() {
        return cache;
    }

    public void setCache(boolean cache) {
        this.cache = cache;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getAjaxOptions() {
        return ajaxOptions;
    }

    public void setAjaxOptions(String ajaxOptions) {
        this.ajaxOptions = ajaxOptions;
    }

    public String getQueryParamsType() {
        return queryParamsType;
    }

    public void setQueryParamsType(String queryParamsType) {
        this.queryParamsType = queryParamsType;
    }

    public boolean isPagination() {
        return pagination;
    }

    public void setPagination(boolean pagination) {
        this.pagination = pagination;
    }

    public boolean isPaginationLoop() {
        return paginationLoop;
    }

    public void setPaginationLoop(boolean paginationLoop) {
        this.paginationLoop = paginationLoop;
    }

    public boolean isOnlyInfoPagination() {
        return onlyInfoPagination;
    }

    public void setOnlyInfoPagination(boolean onlyInfoPagination) {
        this.onlyInfoPagination = onlyInfoPagination;
    }

    public String getSidePagination() {
        return sidePagination;
    }

    public void setSidePagination(String sidePagination) {
        this.sidePagination = sidePagination;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getSelectItemName() {
        return selectItemName;
    }

    public void setSelectItemName(String selectItemName) {
        this.selectItemName = selectItemName;
    }

    public boolean isSmartDisplay() {
        return smartDisplay;
    }

    public void setSmartDisplay(boolean smartDisplay) {
        this.smartDisplay = smartDisplay;
    }

    public boolean isEscape() {
        return escape;
    }

    public void setEscape(boolean escape) {
        this.escape = escape;
    }

    public boolean isSearch() {
        return search;
    }

    public void setSearch(boolean search) {
        this.search = search;
    }

    public boolean isSearchOnEnterKey() {
        return searchOnEnterKey;
    }

    public void setSearchOnEnterKey(boolean searchOnEnterKey) {
        this.searchOnEnterKey = searchOnEnterKey;
    }

    public boolean isStrictSearch() {
        return strictSearch;
    }

    public void setStrictSearch(boolean strictSearch) {
        this.strictSearch = strictSearch;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public int getSearchTimeOut() {
        return searchTimeOut;
    }

    public void setSearchTimeOut(int searchTimeOut) {
        this.searchTimeOut = searchTimeOut;
    }

    public int getTrimOnSearch() {
        return trimOnSearch;
    }

    public void setTrimOnSearch(int trimOnSearch) {
        this.trimOnSearch = trimOnSearch;
    }

    public boolean isShowHeader() {
        return showHeader;
    }

    public void setShowHeader(boolean showHeader) {
        this.showHeader = showHeader;
    }

    public boolean isShowFooter() {
        return showFooter;
    }

    public void setShowFooter(boolean showFooter) {
        this.showFooter = showFooter;
    }

    public boolean isShowColumns() {
        return showColumns;
    }

    public void setShowColumns(boolean showColumns) {
        this.showColumns = showColumns;
    }

    public boolean isShowRefresh() {
        return showRefresh;
    }

    public void setShowRefresh(boolean showRefresh) {
        this.showRefresh = showRefresh;
    }

    public boolean isShowToggle() {
        return showToggle;
    }

    public void setShowToggle(boolean showToggle) {
        this.showToggle = showToggle;
    }

    public boolean isShowPaginationSwitch() {
        return showPaginationSwitch;
    }

    public void setShowPaginationSwitch(boolean showPaginationSwitch) {
        this.showPaginationSwitch = showPaginationSwitch;
    }

    public int getMinimumCountColumns() {
        return minimumCountColumns;
    }

    public void setMinimumCountColumns(int minimumCountColumns) {
        this.minimumCountColumns = minimumCountColumns;
    }

    public String getIdField() {
        return idField;
    }

    public void setIdField(String idField) {
        this.idField = idField;
    }

    public boolean isEditView() {
        return editView;
    }

    public void setEditView(boolean editView) {
        this.editView = editView;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public boolean isCardView() {
        return cardView;
    }

    public void setCardView(boolean cardView) {
        this.cardView = cardView;
    }

    public boolean isDetailView() {
        return detailView;
    }

    public void setDetailView(boolean detailView) {
        this.detailView = detailView;
    }

    public String getSearchAlign() {
        return searchAlign;
    }

    public void setSearchAlign(String searchAlign) {
        this.searchAlign = searchAlign;
    }

    public String getButtonsAlign() {
        return buttonsAlign;
    }

    public void setButtonsAlign(String buttonsAlign) {
        this.buttonsAlign = buttonsAlign;
    }

    public String getToolbarAlign() {
        return toolbarAlign;
    }

    public void setToolbarAlign(String toolbarAlign) {
        this.toolbarAlign = toolbarAlign;
    }

    public String getPaginationVAlign() {
        return paginationVAlign;
    }

    public void setPaginationVAlign(String paginationVAlign) {
        this.paginationVAlign = paginationVAlign;
    }

    public String getPaginationHAlign() {
        return paginationHAlign;
    }

    public void setPaginationHAlign(String paginationHAlign) {
        this.paginationHAlign = paginationHAlign;
    }

    public String getPaginationDetailHAlign() {
        return paginationDetailHAlign;
    }

    public void setPaginationDetailHAlign(String paginationDetailHAlign) {
        this.paginationDetailHAlign = paginationDetailHAlign;
    }

    public String getPaginationPreText() {
        return paginationPreText;
    }

    public void setPaginationPreText(String paginationPreText) {
        this.paginationPreText = paginationPreText;
    }

    public String getPaginationNextText() {
        return paginationNextText;
    }

    public void setPaginationNextText(String paginationNextText) {
        this.paginationNextText = paginationNextText;
    }

    public boolean isClickToSelect() {
        return clickToSelect;
    }

    public void setClickToSelect(boolean clickToSelect) {
        this.clickToSelect = clickToSelect;
    }

    public boolean isSingleSelect() {
        return singleSelect;
    }

    public void setSingleSelect(boolean singleSelect) {
        this.singleSelect = singleSelect;
    }

    public String getToolbar() {
        return toolbar;
    }

    public void setToolbar(String toolbar) {
        this.toolbar = toolbar;
    }

    public boolean isCheckboxHeader() {
        return checkboxHeader;
    }

    public void setCheckboxHeader(boolean checkboxHeader) {
        this.checkboxHeader = checkboxHeader;
    }

    public boolean isMaintainSelected() {
        return maintainSelected;
    }

    public void setMaintainSelected(boolean maintainSelected) {
        this.maintainSelected = maintainSelected;
    }

    public boolean isSortable() {
        return sortable;
    }

    public void setSortable(boolean sortable) {
        this.sortable = sortable;
    }

    public boolean isSilentSort() {
        return silentSort;
    }

    public void setSilentSort(boolean silentSort) {
        this.silentSort = silentSort;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public boolean isShowExport() {
        return showExport;
    }

    public void setShowExport(boolean showExport) {
        this.showExport = showExport;
    }
}
