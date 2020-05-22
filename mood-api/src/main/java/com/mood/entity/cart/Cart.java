package com.mood.entity.cart;

import com.mood.entity.base.BaseEntity;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
// default package

/**
 * 购物车
 * ShopCart entity. @author MyEclipse Persistence Tools
 */
@Data
@Table(name="rebate_cart")
public class Cart extends BaseEntity {
	
	/**
	 * 购物车id
	 */
	@Id
	private String id;
	
	/**
	 * 会员id
	 */
	private String userId;
	
	/**
	 * 店铺id
	 */
	private String storeId;
	
	/**
	 * 店铺名称
	 */
	private String storeName;
	
	/**
	 * 商品id
	 */
	private String goodsId;
	
	/**
	 * 商品名称
	 */
	private String goodsName;
	
	/**
	 * 商品价格
	 */
	private BigDecimal goodsPrice;
	
	/**
	 * 购买商品数量
	 */
	private Integer goodsNum;
	
	/**
	 * 商品图片
	 */
	private String goodsImage;

	/**
	 * 商品规格id
	 */
	private String specId;

	/**
	 * 商品规格内容
	 */
	private String specInfo;

}