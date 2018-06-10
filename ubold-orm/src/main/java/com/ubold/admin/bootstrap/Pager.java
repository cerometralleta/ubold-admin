package com.ubold.admin.bootstrap;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页数据
 * @author zkning
 */
@Data
@AllArgsConstructor
public class Pager<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	private int pageSize;
	private int pageNo;
	private Long totalElements;
	private List<T>	content;
}
