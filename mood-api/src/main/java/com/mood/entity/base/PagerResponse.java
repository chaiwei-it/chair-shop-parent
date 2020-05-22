package com.mood.entity.base;

import com.mood.model.response.RestfulResponse;
import lombok.Data;

@Data
public class PagerResponse extends RestfulResponse {

	/**
	 * 页码
	 */
	private Integer pageIndex;

	/**
	 * 每页显示记录数
	 */
	private Integer pageSize;

	/**
	 * 总页数
	 */
	private Integer maxPages;

	/**
	 * 总记录数
	 */
	private Long total;

	/**
	 * 排序
	 */
	private String orderBy;

	/**
	 * 开始位置
	 */
	private Integer startIndex;


	
}
