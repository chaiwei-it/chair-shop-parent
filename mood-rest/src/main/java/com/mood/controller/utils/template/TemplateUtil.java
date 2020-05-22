//package com.mood.controller.utils.template;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.mood.entity.order.Order;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 模块
// *
// * @author chaiwei
// * @time 2018-07-16 17:14
// */
//public class TemplateUtil {
//
//    public static String accessKeySecret = "9aa683e985520374215beab081b45db5";
//
//    public static final String appid = "wx9edff7d79255744d";
//
//    public static final String messageUrl = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=";
//
//    public static final String orderPayId = "YRDFpUitl3p3trojTkJGD3Riy6w39gqFXNfEGcD1Wjs";
//
//    public static void orderPay(String openid,String formid, Order order) {
//        TemplateData templateData = new TemplateData();
//        templateData.setTouser(openid);//接收者
//        templateData.setTemplate_id(orderPayId);//模板ID
//        templateData.setForm_id(formid);
//        Map<String, Template> map = new HashMap<String, Template>();
//
//        //订单id
//        map.put("keyword1", new Template(order.getOrderSn()));
//        //商品名称
//        map.put("keyword2", new Template("测试商品"));
//        //支付时间
//        order.setPaymentTime(System.currentTimeMillis());
//        map.put("keyword3", new Template(order.getPaymentTime().toString()));
//        //支付方式
//        map.put("keyword4", new Template("微信"));
//        //订单金额
//        map.put("keyword5", new Template(order.getPayPrice().toString()));
//        //下单时间
//        map.put("keyword6", new Template(order.getCreateTime().toString()));
//        //状态
//        map.put("keyword7", new Template(order.getOrderStatus().toString()));
//        templateData.setData(map);
//        String json = JSONObject.toJSONString(templateData);
//        send(json);
//    }
//
//    public static void send(String json){
//        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + accessKeySecret;
//        JSONObject jsonObject = JSON.parseObject(HttpClientUtil.sendGet(url));
//        String token = jsonObject.getString("access_token");
//        System.out.println(HttpClientUtil.sendPost(messageUrl + token, json));
//    }
//
//}
