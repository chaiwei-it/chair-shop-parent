package com.mood.order.service;

import com.mood.base.service.BaseService;
import com.mood.entity.order.request.*;
import com.mood.entity.order.response.*;
import com.mood.entity.order.OrderRebate;
import com.mood.entity.order.Order;
import com.mood.entity.order.request.OrderInsertRequest;
import com.mood.entity.order.response.OrderInsertResponse;
import com.mood.entity.rabate.Wxpay;
import com.mood.model.response.RestfulResponse;
import net.sf.json.JSONObject;

import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface OrderService extends BaseService<Order> {

    public OrderInsertResponse insert(OrderInsertRequest request);

    public OrderUpdateResponse update(OrderUpdateRequest request);

    public OrderDeleteResponse delete(OrderDeleteRequest request);

    public OrderSelectResponse select(OrderSelectRequest request);

    public OrderListResponse selectList(OrderListRequest request);

    public OrderPagerResponse selectPager(OrderPagerRequest request);

    /**
     * 获取订单返利列表
     * @param param
     * @return
     */
    public List<OrderRebate> getOrderRebate(JSONObject param);

    /**
     * 获取用户订单列表
     * @param userId
     * @return
     */
    public List<Order> selectByUserId(String userId);

    /**
     * 发货
     * @param request
     * @return
     */
    public OrderShippingResponse shipping(OrderShippingRequest request);



    /**
     * 取消订单
     * @param id
     * @return
     */
    RestfulResponse cancel(String id);

    /**
     * 确认收货
     * @param id
     * @return
     */
    RestfulResponse confirm(String id);

    /**
     * 订单数量
     * @param userId
     * @return
     */
    OrderNumResponse getOrderNum(String userId);

    /**
     * 支付完成
     * @param wxpay
     * @return
     */
    void payFinish(Wxpay wxpay);
}
