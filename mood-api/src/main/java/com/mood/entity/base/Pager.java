package com.mood.entity.base;

import lombok.Data;

@Data
public class Pager {

	/**
	 * 页码
	 */
	private Integer pageIndex;

	/**
	 * 每页显示记录数
	 */
	private Integer pageSize;

	/**
	 * 排序
	 */
	private String orderBy;


	
}
