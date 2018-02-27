package com.ubold.admin.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="TB_SM_SQLDEFINE")
public class SqlDefine extends Auditable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="sqlid")
	private String sqlId;
	
	@Column(name="sqlname")
	private String sqlName;
	
	@Column(name="selectsql")
	private String selectSql;
	
	@Column(name="sqlexpand")
	private String sqlExpand;
    private Integer datasource;
    /**
	 * 是否缓存
	 */
	private Integer cache;
	
	/**
	 * 1-编辑,2-发布
	 */
	private Integer status;
	
	/**
	 * 功能描述
	 */
	private String sqldesc;
	
	/**
	 * 所属功能组
	 */
	@Column(name="groupid")
	private String groupId;
	
	/**
	 * 主表
	 */
	@Column(name="mastertable")
	private String masterTable;
	
	/**
	 * 主表对应的ID
	 */
	@Column(name="mastertableid")
	private String masterTableId;
	
	public String getMasterTable() {
		return masterTable;
	}
	public void setMasterTable(String masterTable) {
		this.masterTable = masterTable;
	}
	public String getMasterTableId() {
		return masterTableId;
	}
	public void setMasterTableId(String masterTableId) {
		this.masterTableId = masterTableId;
	}
	public String getSqlExpand() {
		return sqlExpand;
	}
	public void setSqlExpand(String sqlExpand) {
		this.sqlExpand = sqlExpand;
	}
	public String getSqldesc() {
		return sqldesc;
	}
	public void setSqldesc(String sqldesc) {
		this.sqldesc = sqldesc;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getSqlId() {
		return sqlId;
	}
	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}
	public String getSqlName() {
		return sqlName;
	}
	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
	}
	 
	public String getSelectSql() {
		return selectSql;
	}
	public void setSelectSql(String selectSql) {
		this.selectSql = selectSql;
	}

    public Integer getDatasource() {
        return datasource;
	}

    public void setDatasource(Integer datasource) {
        this.datasource = datasource;
	}
	public Integer getCache() {
		return cache;
	}
	public void setCache(Integer cache) {
		this.cache = cache;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
}
