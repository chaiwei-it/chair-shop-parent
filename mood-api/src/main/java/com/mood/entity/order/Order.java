package com.mood.entity.order;

import com.mood.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 模块
 *
 * @author chaiwei
 * @time 2018-07-18 9:49
 */
@Data
@Table(name="rebate_order")
public class Order extends BaseEntity {

	/**
	 * 订单索引id
	 */
	@Id
	private String id;
	
	/**
	 * 订单编号，商城内部使用
	 */
	private String orderSn;

	/**
	 * 订单状态：10:待付款;20:待发货;30:待收货;40:已确认;100:已取消;
	 */
	private Integer orderStatus;

	/**
	 * 商品总价格
	 */
	private BigDecimal goodsPrice;

	/**
	 * 邮费
	 */
	private BigDecimal shippingPrice;

	/**
	 * 订单总价格
	 */
	private BigDecimal totalPrice;

	/**
	 * 优惠总价格
	 */
	private BigDecimal discount;

	/**
	 * 应付总价格
	 */
	private BigDecimal shouldPrice;

	/**
	 * 实际支付
	 */
	private BigDecimal payPrice;


	
	/**
	 * 卖家店铺id
	 */
	private String storeId;
	
	/**
	 * 卖家店铺名称
	 */
	private String storeName;

	/**
	 * 卖家店铺电话
	 */
	private String storeMobile;
	
	/**
	 * 买家id
	 */
	private String buyerId;
	
	/**
	 * 买家姓名
	 */
	private String buyerName;
	
	/**
	 * 买电话
	 */
	private String buyerMobile;
	
	/**
	 * 支付方式id
	 */
	private String paymentId;
	
	/**
	 * 支付方式名称
	 */
	private String paymentName;

	/**
	 * 物流单号
	 */
	private String shippingCode;

	/**
	 * 支付时间
	 */
	private Long paymentTime;

	/**
	 * 发货时间
	 */
	private Long shippingTime;

	/**
	 * 订单完成时间
	 */
	private Long finnshedTime;


}