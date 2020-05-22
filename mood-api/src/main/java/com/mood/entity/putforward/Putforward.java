package com.mood.entity.putforward;

import com.mood.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * rebate_agent
 * @author 
 */
@Data
@Table(name="rebate_putforward")
public class Putforward extends BaseEntity {

    @Id
    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 取现金额
     */
    private BigDecimal price;

    /**
     * 取现余额
     */
    private BigDecimal balance;

    /**
     * 支付宝账号
     */
    private String alipayNum;

    /**
     * 支付宝账号
     */
    private String alipayName;

    /**手机号状态（0待提现1已提现2已取消）
     */
    private String mobile;

    /**
     * 状态（0待提现1已提现2已取消）
     */
    private Integer status;

    private String reason;

    /**
     * 申请时间
     */
    private String createData;

    /**
     * 到账时间
     */
    private String putData;

}