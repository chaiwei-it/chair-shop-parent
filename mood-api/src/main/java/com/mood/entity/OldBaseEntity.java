package com.mood.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OldBaseEntity {

	/** 创建时间-数据库字段
	 *
	 */
    private Long createTime;
	/** 修改时间-数据库字段 */
	private Long updateTime;
	/** 开始时间-数据库字段 */
	private Long startTime;
	/** 结束时间-数据库字段 */
	private Long endTime;
	/** 创建人 */
	private String createPerson;
	/** 修改人  */
	private String updatePerson;
	/** 创建时间－页面字段 */
	private Timestamp createTimeStr;
	/** 修改时间－页面字段 */
	private Timestamp updateTimeStr;
	/** 开始时间－页面字段 */
	private Timestamp startTimeStr;
	/** 结束时间－页面字段 */
	private Timestamp endTimeStr;
	/** 0:未删除;1.已删除 */
	private int isDel;

}
