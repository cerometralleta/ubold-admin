package com.ubold.admin.pager;

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
	private int pageSize;
	private int pageNo;
	private Long totalElements;
	private List<T>	content;
}
