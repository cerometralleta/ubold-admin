package com.ubold.admin.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页数据
 * @author zkning
 */
@Data
public class Pager<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer pageSize;
	private Integer pageNo;
	private Integer totalElements;
	private List<T>	content;
}
