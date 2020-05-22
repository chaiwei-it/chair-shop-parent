package com.mood.order.service.impl;

import com.mood.Shift;
import com.mood.StatusCode;
import com.mood.address.service.AddressService;
import com.mood.agent.service.AgentService;
import com.mood.base.Pager;
import com.mood.cart.dao.CartDao;
import com.mood.entity.address.Address;
import com.mood.entity.agent.Agent;
import com.mood.entity.cart.Cart;
import com.mood.entity.cart.response.CartGoodsDetailsResponse;
import com.mood.entity.goods.Goods;
import com.mood.entity.goodsSpec.GoodsSpec;
import com.mood.entity.order.Order;
import com.mood.entity.order.OrderRebate;
import com.mood.entity.order.request.*;
import com.mood.entity.order.response.*;
import com.mood.entity.orderAddress.OrderAddress;
import com.mood.entity.orderGoods.OrderGoods;
import com.mood.entity.rabate.Wxpay;
import com.mood.entity.userRebate.UserRebate;
import com.mood.goods.dao.GoodsDao;
import com.mood.goodsSpec.dao.GoodsSpecDao;
import com.mood.model.response.RestfulResponse;
import com.mood.order.dao.OrderDao;
import com.mood.order.service.OrderService;
import com.mood.orderAddress.service.OrderAddressService;
import com.mood.orderGoods.service.OrderGoodsService;
import com.mood.rebate.service.RebateService;
import com.mood.template.service.TemplateService;
import com.mood.userRebate.service.UserRebateService;
import com.mood.utils.IdGen;
import com.mood.utils.IdUtil;
import com.mood.utils.OrikaMapper;
import com.mood.wxpay.service.WxpayService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private CartDao cartDao;

    @Autowired
    private UserRebateService userRebateService;

    @Autowired
    private AgentService agentService;

    @Autowired
    private RebateService rebateService;

    @Autowired
    private OrderGoodsService orderGoodsService;

    @Autowired
    private OrderAddressService orderAddressService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private GoodsSpecDao goodsSpecDao;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private WxpayService wxpayService;

    @Override
    public int insert(Order order) {
        order.setCreateTime(System.currentTimeMillis());
        return orderDao.insert(order);
    }

    @Override
    public int update(Order order) {
        return orderDao.update(order);
    }

    @Override
    public int deleteById(String id) {
        return orderDao.deleteById(id);
    }

    @Override
    public Order selectById(String id) {
        return orderDao.selectById(id);
    }

    @Override
    public List<Order> selectAll(JSONObject param) {
        return orderDao.selectAll(param);
    }

    @Override
    public Pager<Order> selectPager(Pager pager){
        return orderDao.selectPager(pager);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderInsertResponse insert(OrderInsertRequest request){
        Order order = new Order();
        order.setId(IdGen.uuid());
        //处理订单商品
        String[] ids = request.getCartIds().split(",");
        List<CartGoodsDetailsResponse> list = new ArrayList<CartGoodsDetailsResponse>();
        BigDecimal goodsPrice = new BigDecimal(0);

        for(String cartId : ids) {
            Cart cart = cartDao.selectById(cartId);
            if(cart != null){
                OrderGoods orderGoods = new OrderGoods();
                Goods goods = goodsDao.selectById(cart.getGoodsId());
                if(goods.getStock() - cart.getGoodsNum() < 0){
                    Shift.fatal(StatusCode.NOT_STOCK);
                }
                orderGoods.setGoodsId(goods.getId());
                orderGoods.setGoodsName(goods.getName());
                orderGoods.setGoodsImage(goods.getThumbnailImage());
                orderGoods.setGoodsNum(cart.getGoodsNum());
                if (cart.getSpecId() == null || "".equals(cart.getSpecId())) {
                    orderGoods.setGoodsPrice(goods.getNowPrice());
                    goodsPrice = goodsPrice.add(goods.getNowPrice().multiply(new BigDecimal(cart.getGoodsNum())));
                } else {
                    GoodsSpec goodsSpec = goodsSpecDao.selectById(cart.getSpecId());
                    orderGoods.setSpecId(goodsSpec.getId());
                    orderGoods.setSpecInfo(goodsSpec.getSpecNames());
                    orderGoods.setGoodsPrice(goodsSpec.getNowPrice());
                    goodsPrice = goodsPrice.add(goodsSpec.getNowPrice().multiply(new BigDecimal(cart.getGoodsNum())));
                }
                orderGoods.setOrderId(order.getId());
                orderGoods.setStoresId("index");
                orderGoodsService.insert(orderGoods);
                goods.setStock(goods.getStock() - cart.getGoodsNum());
                goods.setSales(goods.getSales() + cart.getGoodsNum());
                goodsDao.update(goods);
            }
            cartDao.deleteById(cartId);
        }
        //处理收货
        Address address = addressService.selectById(request.getAddressId());
        OrderAddress orderAddress = OrikaMapper.map(address, OrderAddress.class);
        orderAddress.setAddress(address.getProvinceName() + address.getCityName() + address.getAreaName() + address.getAddress());
        orderAddress.setOrderId(order.getId());
        orderAddressService.insert(orderAddress);
        //处理订单
        order.setOrderSn("mm" + IdUtil.generateCode());
        order.setOrderStatus(10);
        order.setGoodsPrice(goodsPrice);
        order.setShippingPrice(new BigDecimal(0));
        order.setTotalPrice(goodsPrice.add(order.getShippingPrice()));
        order.setDiscount(new BigDecimal(0));
        order.setShouldPrice(order.getTotalPrice().subtract(order.getDiscount()));
        order.setPayPrice(new BigDecimal(0));
        order.setStoreId("index");
        order.setStoreName("平台自营");
        order.setStoreMobile("");
        UserRebate userRebate = userRebateService.selectById(request.getBuyerId());
        order.setBuyerId(userRebate.getId());
        order.setBuyerName(userRebate.getUsername());
        order.setBuyerMobile(userRebate.getMobile());
        order.setCreateTime(System.currentTimeMillis());
        orderDao.insert(order);
        OrderInsertResponse orderInsertResponse = new OrderInsertResponse();
        orderInsertResponse.setOrderId(order.getId());
        return orderInsertResponse;

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderUpdateResponse update(OrderUpdateRequest request){
        Order order = orderDao.selectById(request.getId());
        order.setShippingCode(request.getShippingCode());
        if(order.getOrderStatus() == 20){
            order.setOrderStatus(30);
        }
        orderDao.update(order);
        OrderAddress orderAddress= orderAddressService.selectByOrderId(request.getId());
        orderAddress.setUsername(request.getUsername());
        orderAddress.setMobile(request.getMobile());
        orderAddress.setAddress(request.getAddress());
        orderAddressService.update(orderAddress);
        return OrikaMapper.map(order, OrderUpdateResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderDeleteResponse delete(OrderDeleteRequest request){
        Order order = orderDao.selectById(request.getId());
        if(order != null){
            orderDao.deleteById(order.getId());
        }
        return new OrderDeleteResponse();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderSelectResponse select(OrderSelectRequest request){
        Order order = orderDao.selectById(request.getId());
        OrderSelectResponse orderSelectResponse = OrikaMapper.map(order, OrderSelectResponse.class);
        orderSelectResponse.setGoodsList(orderGoodsService.selectByOrderId(request.getId()));
        orderSelectResponse.setAddress(orderAddressService.selectByOrderId(request.getId()));
        return orderSelectResponse;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderListResponse selectList(OrderListRequest request){
        Order order = OrikaMapper.map(request, Order.class);
        List<Order> orderList = orderDao.selectAll(order);
        List<OrderDetailsResponse> detailsList = OrikaMapper.mapList(orderList, OrderDetailsResponse.class);
        OrderListResponse response = new OrderListResponse();
        for(OrderDetailsResponse orderDetailsResponse :detailsList){
            orderDetailsResponse.setGoodsList(orderGoodsService.selectByOrderId(orderDetailsResponse.getId()));
        }
        response.setList(detailsList);
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderPagerResponse selectPager(OrderPagerRequest request){
        Order order = OrikaMapper.map(request, Order.class);
        Pager<Order> pager = OrikaMapper.map(request, Pager.class);
        pager.setObject(order);
        pager = orderDao.selectPager(pager);
        List<OrderDetailsResponse> detailsList = OrikaMapper.mapList(pager.getData(), OrderDetailsResponse.class);
        OrderPagerResponse response = OrikaMapper.map(pager, OrderPagerResponse.class);
        response.setList(detailsList);
        return response;
    }

    public Integer agentNotify(Wxpay wxpay){

        return 1;
    }


    /**
     * 获取订单返利列表
     * @param param
     * @return
     */
    @Override
    public List<OrderRebate> getOrderRebate(JSONObject param){
        List<Order> orderList = orderDao.selectAll(param);
        List<OrderRebate> orderRebateList = OrikaMapper.mapList(orderList, OrderRebate.class);
        for (OrderRebate orderRebate: orderRebateList) {
            JSONObject rebateParam = new JSONObject();
            rebateParam.put("orderId", orderRebate.getOrderId());
            orderRebate.setRebateList(rebateService.selectAll(rebateParam));
        }
        return orderRebateList;
    }

    /**
     * 获取用户订单列表
     * @param userId
     * @return
     */
    @Override
    public List<Order> selectByUserId(String userId){
        JSONObject param = new JSONObject();
        param.put("buyerId", userId);
        return orderDao.selectAll(param);
    }

    /**
     * 发货
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderShippingResponse shipping(OrderShippingRequest request){
        Order order = selectById(request.getId());
        if(order != null){
            order.setOrderStatus(30);
            order.setShippingCode(request.getShippingCode());
            order.setShippingTime(System.currentTimeMillis());
            orderDao.update(order);
            templateService.send(request.getId());
        }
        return new OrderShippingResponse();
    }

    /**
     * 取消订单
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public RestfulResponse cancel(String id){
        Order order = selectById(id);
        order.setOrderStatus(0);
        orderDao.update(order);
        return new RestfulResponse();
    }

    /**
     * 确认收货
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public RestfulResponse confirm(String id){
        Order order = selectById(id);
        order.setOrderStatus(40);
        order.setFinnshedTime(System.currentTimeMillis());
        orderDao.update(order);
        templateService.finish(id);
        return new RestfulResponse();
    }

    /**
     * 订单数量
     * @param userId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderNumResponse getOrderNum(String userId){
        OrderNumResponse orderNumResponse = new OrderNumResponse();
        Order order = new Order();
        order.setBuyerId(userId);
        order.setOrderStatus(10);
        List<Order> orderList = orderDao.selectAll(order);
        orderNumResponse.setNotPay(orderList.size());
        order.setOrderStatus(20);
        orderList = orderDao.selectAll(order);
        orderNumResponse.setNotCollect(orderList.size());
        order.setOrderStatus(30);
        orderList = orderDao.selectAll(order);
        orderNumResponse.setNotDeliver(orderList.size());
        order.setOrderStatus(40);
        orderList = orderDao.selectAll(order);
        orderNumResponse.setOrderFinish(orderList.size());
        return orderNumResponse;
    }

    /**
     * 支付完成
     * @param wxpay
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void payFinish(Wxpay wxpay){
        //修改支付状态
        wxpayService.update(wxpay);
        //修改订单状态
        Order order = selectById(wxpay.getOrderId());
        if(order == null){
            System.out.println("当前订单不存在：时间-" + new Date() + "内容" + wxpay);
        }
        order.setOrderStatus(20);
        order.setPaymentTime(System.currentTimeMillis());
        order.setPayPrice(wxpay.getPayPrice());
        update(order);
        //模板通知
        templateService.pay(order.getId());
        //返利
        rebateService.orderRebate(wxpay.getOrderId());
        //升级
        UserRebate userRebate = userRebateService.selectById(wxpay.getUserId());
        if(userRebate.getGrade() < 1){
            userRebate.setGrade(1);
            userRebateService.update(userRebate);
        }
    }
}
