package com.ubold.admin.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Data
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
    private String datasource;
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
}
