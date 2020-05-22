package com.mood.entity.putforward.response;

import com.mood.entity.base.BaseEntity;
import com.mood.entity.userRebate.UserRebate;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-06-04 下午16:00
 */
@Data
public class PutforwardDetailsResponse extends BaseEntity {

    @ApiModelProperty(value = "测试ID", example = "1", required = true)
    private String id;

    /**
     * 名称
     */
    private String userId;

    private UserRebate userRebate;

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