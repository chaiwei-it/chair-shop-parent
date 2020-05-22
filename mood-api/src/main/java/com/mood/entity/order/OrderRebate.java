package com.mood.entity.order;

import com.mood.entity.rabate.Rebate;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.List;

/**
 * 模块
 *
 * @author chaiwei
 * @time 2018-07-18 9:49
 */
@Data
public class OrderRebate {
    /**
     * 订单索引id
     */
    @Id
    private String orderId;

    /**
     * 订单编号，商城内部使用
     */
    private String orderSn;

    /**
     * 卖家店铺id
     */
    private String storeId;

    /**
     * 卖家店铺名称
     */
    private String storeName;

    /**
     * 买家id
     */
    private String buyerId;

    /**
     * 买家姓名
     */
    private String buyerName;

    /**
     * 买家电子邮箱
     */
    private String buyerEmail;

    private Long createTime;

    /**
     * 订单类型 0.普通 1.团购 2虚拟商品
     */
    private Integer orderType;

    /**
     * 来自什么平台的订单 默认 0 pc，1 安卓，2 ios
     */
    private int orderPlatform;

    /**
     * 支付方式id
     */
    private String paymentId;

    /**
     * 支付方式名称
     */
    private String paymentName;

    /**
     * 支付方式名称代码
     */
    private String paymentCode;

    /**
     * 支付分支
     */
    private String paymentBranch;

    /**
     * 支付类型:1是即时到帐,2是担保交易
     */
    private String paymentDirect;

    /**
     * 付款状态:0:未付款;1:已付款
     */
    private Integer paymentState;

    /**
     * 订单编号，外部支付时使用，有些外部支付系统要求特定的订单编号
     */
    private String outSn;

    /**
     * 交易流水号
     */
    private String tradeSn;

    /**
     * 支付(付款)时间
     */
    private Long paymentTime;

    /**
     * 支付留言
     */
    private String payMessage;

    /**
     * 配送时间
     */
    private Long shippingTime;

    /**
     * 配送公司ID
     */
    private String shippingExpressId;

    /**
     * 物流单号
     */
    private String shippingCode;

    /**
     * 外部交易平台单独使用的标识字符串
     */
    private String outPaymentCode;

    /**
     * 订单完成时间
     */
    private Long finnshedTime;

    /**
     * 发票信息
     */
    private String invoice;


    /**
     * 商品总价格
     */
    private BigDecimal goodsAmount;

    /**
     * 优惠总金额
     */
    private BigDecimal discount;

    /**
     * 订单应付金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单总价格
     */
    private BigDecimal orderTotalPrice;

    /**
     * 运费价格
     */
    private BigDecimal shippingFee;

    /**
     * 配送方式
     */
    private String shippingName;


    /**
     * 评价状态 0为评价，1已评价
     */
    private Integer evaluationStatus;

    /**
     * 评价时间
     */
    private Long evaluationTime;

    /**
     * 卖家是否已评价买家
     */
    private Integer evalsellerStatus;

    /**
     * 卖家评价买家的时间
     */
    private Long evalsellerTime;

    /**
     * 订单留言
     */
    private String orderMessage;

    /**
     * 订单状态：0:已取消;10:待付款;20:待发货;30:待收货;40:交易完成;50:已提交;60:已确认;
     */
    private Integer orderState;

    /**
     * 订单赠送积分
     */
    private Integer orderPointscount;

    /**
     * 代金券id
     */
    private String voucherId;

    /**
     * 代金券面额
     */
    private BigDecimal voucherPrice;

    /**
     * 代金券编码
     */
    private String voucherCode;

    /**
     * 退款状态:0是无退款,1是部分退款,2是全部退款
     */
    private Integer refundState;

    /**
     * 退货状态:0是无退货,1是部分退货,2是全部退货
     */
    private Integer returnState;


    /**
     * 退款金额
     */
    private BigDecimal refundAmount;

    /**
     * 退货数量
     */
    private Integer returnNum;

    /**
     * 团购编号(非团购订单为0)
     */
    private String groupId;

    /**
     * 团购数量
     */
    private Integer groupCount;

    /**
     *  限时折扣编号
     */
    private String xianshiId;

    /**
     * 限时折扣说明
     */
    private String xianshiExplain;

    /**
     * 满就送编号
     */
    private String mansongId;

    /**
     * 满就送说明
     */
    private String mansongExplain;

    /**
     * 搭配套餐id
     */
    private String bundlingId;

    /**
     * 搭配套餐说明
     */
    private String bundlingExplain;

    /**
     * 1PC2手机端
     */
    private String orderFrom;

    /**
     * 发货备注
     */
    private String deliverExplain;

    /**
     * 发货地址ID
     */
    private String daddressId;

    /**
     * 收货地址id
     */
    private String addressId;

    /**
     * 订单支付表id
     */
    private String payId;

    /**
     * 订单支付表编号
     */
    private String paySn;

    /**
     * 结算状态:0未结算,1已结算
     */
    private Integer balanceState;

    /**
     * 结算时间
     */
    private Long balanceTime;

    private String shippingExpressCode;

    private BigDecimal predepositAmount;

    private String cancelCause;

    private String couponId;

    private BigDecimal couponPrice;

    private BigDecimal promoPrice;

    /**
     * 锁定状态:0是正常,大于0是锁定,默认是0
     */
    private Integer lockState;

    private Integer barterState;

    /**
     * 换货数量
     */
    private Integer barterNum;

    private Integer usePointNum;  //订单所用积分
    private Integer pointRmbNum; //积分抵扣金额

    private List<Rebate> rebateList;
}
