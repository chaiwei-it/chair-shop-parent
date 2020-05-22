package com.mood.template.service.impl;

import com.mood.entity.order.Order;
import com.mood.entity.orderAddress.OrderAddress;
import com.mood.entity.orderGoods.OrderGoods;
import com.mood.entity.rabate.UserOpen;
import com.mood.entity.rabate.Wxpay;
import com.mood.order.dao.OrderDao;
import com.mood.orderAddress.service.OrderAddressService;
import com.mood.orderGoods.service.OrderGoodsService;
import com.mood.template.service.TemplateService;
import com.mood.userOpen.service.UserOpenService;
import com.mood.userRebate.dao.UserRebateDao;
import com.mood.utils.template.Template;
import com.mood.utils.template.TemplateUtil;
import com.mood.wxpay.service.WxpayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateUtil templateUtil;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserOpenService userOpenService;

    @Autowired
    private OrderGoodsService orderGoodsService;

    @Autowired
    private WxpayService wxpayService;

    @Autowired
    private OrderAddressService orderAddressService;

    @Override
    public void pay(String orderId) {
        Map<String, Template> data = new HashMap<String, Template>();
        Order order = orderDao.selectById(orderId);
        Wxpay wxpay = wxpayService.selectByOrderId(orderId);
        //商品名称
        List<OrderGoods> orderGoodsList = orderGoodsService.selectByOrderId(orderId);
        if(orderGoodsList.size() > 1){
            data.put("keyword1", new Template(orderGoodsList.get(0).getGoodsName() + "等商品"));
        }else{
            data.put("keyword1", new Template(orderGoodsList.get(0).getGoodsName()));
        }
        //支付时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");//这个是你要转成后的时间的格式
        String paymentTime = sdf.format(new Date(order.getPaymentTime()));   // 时间戳转换成时间
        data.put("keyword2", new Template(paymentTime));
        //订单金额
        data.put("keyword3", new Template(order.getPayPrice().toString() + "元"));
        //订单id
        data.put("keyword4", new Template(order.getOrderSn()));
        //支付时间
        String createTime = sdf.format(new Date(order.getCreateTime()));   // 时间戳转换成时间
        data.put("keyword5", new Template(createTime));
        templateUtil.sendTemplate(wxpay.getOpenId(), orderId, data, wxpay.getPrepayId(), 1);
    }

    @Override
    public void send(String orderId) {
        Map<String, Template> data = new HashMap<String, Template>();
        Order order = orderDao.selectById(orderId);
        Wxpay wxpay = wxpayService.selectByOrderId(orderId);
        //订单id
        data.put("keyword1", new Template(order.getOrderSn()));
        //商品名称
        List<OrderGoods> orderGoodsList = orderGoodsService.selectByOrderId(orderId);
        if(orderGoodsList.size() > 1){
            data.put("keyword2", new Template(orderGoodsList.get(0).getGoodsName() + "等商品"));
        }else{
            data.put("keyword2", new Template(orderGoodsList.get(0).getGoodsName()));
        }
        //支付时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");//这个是你要转成后的时间的格式
        String paymentTime = sdf.format(new Date(order.getPaymentTime()));   // 时间戳转换成时间
        data.put("keyword3", new Template(paymentTime));
        //物流公司
        data.put("keyword4", new Template("申通快递"));
        //快递单号
        data.put("keyword5", new Template(order.getShippingCode()));
        //订单id
        String shippingTime = sdf.format(new Date(order.getShippingTime()));   // 时间戳转换成时间
        data.put("keyword6", new Template(shippingTime));
        data.put("keyword7", new Template("预计三天内送达，请保持电话畅通"));
        templateUtil.sendTemplate(wxpay.getOpenId(), orderId, data, wxpay.getPrepayId(), 2);
    }

    @Override
    public void finish(String orderId) {
        Map<String, Template> data = new HashMap<String, Template>();
        Order order = orderDao.selectById(orderId);
        Wxpay wxpay = wxpayService.selectByOrderId(orderId);
        //订单id
        data.put("keyword1", new Template(order.getOrderSn()));
        //商品名称
        List<OrderGoods> orderGoodsList = orderGoodsService.selectByOrderId(orderId);
        if(orderGoodsList.size() > 1){
            data.put("keyword2", new Template(orderGoodsList.get(0).getGoodsName() + "等商品"));
        }else{
            data.put("keyword2", new Template(orderGoodsList.get(0).getGoodsName()));
        }
        //订单时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");//这个是你要转成后的时间的格式
        String paymentTime = sdf.format(new Date(order.getPaymentTime()));   // 时间戳转换成时间
        data.put("keyword3", new Template(paymentTime));
        OrderAddress orderAddress = orderAddressService.selectByOrderId(orderId);
        //收货人
        data.put("keyword4", new Template(orderAddress.getUsername()));
        //收货人电话
        data.put("keyword5", new Template(orderAddress.getMobile()));
        //收货时间
        String finnshedTime = sdf.format(new Date(order.getFinnshedTime()));   // 时间戳转换成时间
        data.put("keyword6", new Template(finnshedTime));
        data.put("keyword7", new Template("您的订单已签收"));
        templateUtil.sendTemplate(wxpay.getOpenId(), orderId, data, wxpay.getPrepayId(), 3);
    }
}
