package com.mood.entity.rabate;

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
@Table(name="rebate_wxpay")
public class Wxpay extends BaseEntity {

    @Id
    private String id;

    private String userId;

    private String openId;

    private String orderId;

    private Integer orderType;

    private BigDecimal shouldPayPrice;

    private BigDecimal payPrice;

    private Integer payStatus;

    private String payId;

    private String prepayId;

    private String timeStamp;

    private String paySign;

    private Integer appid;

    private String nonceStr;

}