package com.ubold.admin.model;

import java.io.Serializable;

/**
 * 数据类型
 * @author zkning
 */
public class DataTypeResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type;
	private String typePackage;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypePackage() {
		return typePackage;
	}
	public void setTypePackage(String typePackage) {
		this.typePackage = typePackage;
	}
}
