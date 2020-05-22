package com.mood.utils.template;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mood.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 模块
 *
 * @author chaiwei
 * @time 2018-07-16 17:14
 */
@Component
public class TemplateUtil {

    public static String accessKeySecret = "9aa683e985520374215beab081b45db5";

    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.messageUrl}")
    private String messageUrl;

    @Value("${wx.token}")
    private String token;

    @Value("${wx.orderPayId}")
    private String orderPayId;

    @Value("${wx.sendGoodsId}")
    private String sendGoodsId;

    @Value("${wx.receiveGoodsId}")
    private String receiveGoodsId;


//    public static final String messageUrl = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=";

    //支付成功
//    public static final String orderPayId = "YRDFpUitl3p3trojTkJGD3Riy6w39gqFXNfEGcD1Wjs";

    //发货
//    public static final String sendGoodsId = "YRDFpUitl3p3trojTkJGD3Riy6w39gqFXNfEGcD1Wjs";

    //收货
//    public static final String receiveGoodsId = "YRDFpUitl3p3trojTkJGD3Riy6w39gqFXNfEGcD1Wjs";

    public void sendTemplate(String openid, String orderId, Map<String, Template> data, String formid, Integer sentType) {
        TemplateData templateData = new TemplateData();
        templateData.setTouser(openid);//接收者
        switch (sentType){
            case 1: {
                templateData.setTemplate_id(orderPayId);//模板ID
                break;
            }
            case 2: {
                templateData.setTemplate_id(sendGoodsId);//模板ID
                break;
            }
            case 3: {
                templateData.setTemplate_id(receiveGoodsId);//模板ID
                break;
            }
        }
        templateData.setPage("pages/order/details?id=" + orderId);
        templateData.setForm_id(formid);
        templateData.setData(data);
        String json = JSONObject.toJSONString(templateData);
        send(json);
    }

    public void send(String json){
        String url = token + "?grant_type=client_credential&appid=" + appid + "&secret=" + accessKeySecret;
        JSONObject jsonObject = JSON.parseObject(HttpClientUtil.sendGet(url));
        String token = jsonObject.getString("access_token");
        System.out.println(HttpClientUtil.sendPost(messageUrl + "?access_token=" + token, json));
    }

}
