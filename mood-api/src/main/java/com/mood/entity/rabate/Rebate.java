package com.mood.entity.rabate;

import com.mood.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * rebate_code
 * @author 
 */
@Data
@Table(name="rebate_rebate")
public class Rebate extends BaseEntity {

    @Id
    private String id;

    private String orderId;

    /**
     * 购买者ID
     */
    private String buyId;

    /**
     * 购买者名称
     */
    private String buyName;

    /**
     * 购买者编号
     */
    private String buyNum;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户编号
     */
    private String userNum;

    /**
     * 红包奖励级别
     */
    private Integer gradeType;

    /**
     * 红包奖励级别
     */
    private Integer level;

    /**
     * 返利金额
     */
    private BigDecimal rabatePrice;

    /**
     * 创建日期
     */
    private String createData;

    /**
     * 日期
     */
    private String rabateData;

    /**
     * 状态（0.代支付 1.已支付 2.已取消）
     */
    private Integer status;

    /**
     * 类型（1.购物 2.升级）
     */
    private Integer rebateType;


}