package com.mood.controller.wxpay;

import com.mood.agent.service.AgentService;
import com.mood.base.BaseController;
import com.mood.common.HttpCode;
import com.mood.controller.utils.*;
import com.mood.entity.agent.Agent;
import com.mood.entity.order.Order;
import com.mood.entity.rabate.UserOpen;
import com.mood.entity.rabate.Wxpay;
import com.mood.order.service.OrderService;
import com.mood.rebate.service.RebateService;
import com.mood.userOpen.service.UserOpenService;
import com.mood.userRebate.service.UserRebateService;
import com.mood.utils.IdUtil;
import com.mood.wxpay.service.WxpayService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模块
 * @author chaiwei
 * @time 2018-05-15 下午10:00
 */
@RestController
@RequestMapping("/api/{version}/wxpay")
public class WxpayApi extends BaseController {



    @Autowired
    private WxpayService wxpayService;

    @Autowired
    private UserOpenService userOpenService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AgentService agentService;

    @Value("${wx.appid}")
    private String appid1;

    @Value("${wx.appid2}")
    private String appid2;

    @Value("${wx.mch_id}")
    private String mch_id;

    @Value("${wx.key}")
    private String key;

    @Value("${wx.notify_url}")
    private String notify_url;

    @Value("${wx.SIGNTYPE}")
    private String SIGNTYPE;

    @Value("${wx.TRADETYPE}")
    private String TRADETYPE;

    @Value("${wx.pay_url}")
    private String pay_url;

    /**
     * 详情
     * @param
     * @return
     */
    @PostMapping("select")
    public ResponseEntity<ModelMap> select(
            @RequestParam(value = "id", required = false, defaultValue = "") String id,
            ModelMap modelMap, HttpServletRequest request){
        Wxpay wxpay = wxpayService.selectById(id);
        return setSuccessModelMap(modelMap, wxpay);
    }

    /**
     * 支付下单
     * @param orderId
     * @return
     */
    @PostMapping("")
    public ResponseEntity<ModelMap> create(
            @RequestParam(value = "orderId", required = false, defaultValue = "") String orderId,
            @RequestParam(value = "orderType", required = false, defaultValue = "") Integer orderType,
            @RequestParam(value = "appId", required = false, defaultValue = "") Integer appId,
            ModelMap modelMap, HttpServletRequest request){
        String userId = getUserId(request);
        if(userId == null){
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        }
        JSONObject wxpayParam = new JSONObject();
        wxpayParam.put("orderId",orderId);
        wxpayParam.put("orderType",orderType);
        List<Wxpay> list = wxpayService.selectAll(wxpayParam);
        for(Wxpay wxpay: list){
            wxpayService.deleteById(wxpay.getId());
        }
        JSONObject param = new JSONObject();
        param.put("appId",appId);
        param.put("userId",userId);
        List<UserOpen> userOpenList =  userOpenService.selectAll(param);
        UserOpen userOpen = userOpenList.get(0);
        Wxpay wxpay = new Wxpay();
        Order order = new Order();
        if(orderType == 1){
            order = orderService.selectById(orderId);
            wxpay.setShouldPayPrice(order.getShouldPrice());
        }else if(orderType == 2){
            Agent agent = agentService.selectById(orderId);
            wxpay.setShouldPayPrice(agent.getPrice());
        }
        wxpay.setId("wx" + IdUtil.generateCode());
        wxpay.setAppid(appId);
        wxpay.setOrderId(orderId);
        wxpay.setUserId(userId);
        wxpay.setOpenId(userOpen.getOpenId());
        wxpay.setOrderType(orderType);
        wxpay.setPayStatus(0);
        String spbillCreateIp = IpUtils.getIpAddr(request);
        wxpay = wxPay(wxpay, userOpen.getOpenId(), spbillCreateIp);
        wxpayService.insert(wxpay);
        return setSuccessModelMap(modelMap, wxpay);
    }

    /**
     * @Description: 统一下单
     * @param
     * @param
     */
    public Wxpay wxPay(Wxpay wxpay,String openid, String spbill_create_ip){
        Json json = new Json();
        try{
            String appid = "";
            if(wxpay.getAppid() == 1){
                appid = appid1;
            }else if(wxpay.getAppid() == 2){
                appid = appid2;
            }
            //生成的随机字符串
            String nonce_str = StringUtils.getRandomStringByLength(32);
            //商品名称
            String body = "济宁市颂妆生物科技有限公司";


//            String orderNo = "mood123456789";
            String money = (new Integer(wxpay.getShouldPayPrice().multiply(new BigDecimal(100)).intValue())).toString();//支付金额，单位：分，这边需要转成字符串类型，否则后面的签名会失败

            Map<String, String> packageParams = new HashMap<String, String>();
            packageParams.put("appid", appid);
            packageParams.put("mch_id", mch_id);
            packageParams.put("nonce_str", nonce_str);
            packageParams.put("body", body);
            packageParams.put("out_trade_no", wxpay.getId());//商户订单号
            packageParams.put("total_fee", money);//支付金额，这边需要转成字符串类型，否则后面的签名会失败
            packageParams.put("spbill_create_ip", spbill_create_ip);
            packageParams.put("notify_url", notify_url);
            packageParams.put("trade_type", TRADETYPE);
            packageParams.put("openid", openid);

            // 除去数组中的空值和签名参数
            packageParams = PayUtil.paraFilter(packageParams);
            String prestr = PayUtil.createLinkString(packageParams); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串

            //MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
            String mysign = PayUtil.sign(prestr, key, "utf-8").toUpperCase();
            System.out.println("=======================第一次签名：" + mysign + "=====================");

            //拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
            String xml = "<xml>" + "<appid>" + appid + "</appid>"
                    + "<body><![CDATA[" + body + "]]></body>"
                    + "<mch_id>" + mch_id + "</mch_id>"
                    + "<nonce_str>" + nonce_str + "</nonce_str>"
                    + "<notify_url>" + notify_url + "</notify_url>"
                    + "<openid>" + openid + "</openid>"
                    + "<out_trade_no>" + wxpay.getId() + "</out_trade_no>"
                    + "<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>"
                    + "<total_fee>" + money + "</total_fee>"
                    + "<trade_type>" + TRADETYPE + "</trade_type>"
                    + "<sign>" + mysign + "</sign>"
                    + "</xml>";

            System.out.println("调试模式_统一下单接口 请求XML数据：" + xml);

            //调用统一下单接口，并接受返回的结果
            String result = PayUtil.httpRequest(pay_url, "POST", xml);

            System.out.println("调试模式_统一下单接口 返回XML数据：" + result);

            // 将解析结果存储在HashMap中
            Map map = PayUtil.doXMLParse(result);

            String return_code = (String) map.get("return_code");//返回状态码

            //返回给移动端需要的参数
            Map<String, Object> response = new HashMap<String, Object>();
            if(return_code == "SUCCESS" || return_code.equals(return_code)){
                // 业务结果
                String prepay_id = (String) map.get("prepay_id");//返回的预付单信息
                response.put("nonceStr", nonce_str);
                response.put("package", "prepay_id=" + prepay_id);
                Long timeStamp = System.currentTimeMillis() / 1000;
                response.put("timeStamp", timeStamp + "");//这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误

                String stringSignTemp = "appId=" + appid + "&nonceStr=" + nonce_str + "&package=prepay_id=" + prepay_id+ "&signType=" + SIGNTYPE + "&timeStamp=" + timeStamp;
                //再次签名，这个签名用于小程序端调用wx.requesetPayment方法
                String paySign = PayUtil.sign(stringSignTemp, key, "utf-8").toUpperCase();
                System.out.println("=======================第二次签名：" + paySign + "=====================");
                response.put("paySign", paySign);

                //更新订单信息
                //业务逻辑代码
                wxpay.setNonceStr(nonce_str);
                wxpay.setPaySign(paySign);
                wxpay.setPrepayId(prepay_id);
                wxpay.setTimeStamp(timeStamp + "");
            }

            response.put("appid", appid);

            json.setSuccess(true);
            json.setData(response);
        }catch(Exception e){
            e.printStackTrace();
            json.setSuccess(false);
            json.setMsg("发起失败");
        }
        return wxpay;
    }

    /**
     * 支付回调
     * @param
     * @return
     */
    @RequestMapping(value="/wxNotify")
    public void wxNotify(HttpServletRequest request,HttpServletResponse response)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine())!=null){
            sb.append(line);
        }
        br.close();
        //sb为微信返回的xml
        String notityXml = sb.toString();
        String resXml = "";
        System.out.println("接收到的报文：" + notityXml);

        Map map = PayUtil.doXMLParse(notityXml);

        String returnCode = (String) map.get("return_code");
        if("SUCCESS".equals(returnCode)){
            //验证签名是否正确
//            if(PayUtil.verify(PayUtil.createLinkString(map), (String)map.get("sign"), WxPayConfig.key, "utf-8")){
            /**此处添加自己的业务逻辑代码start**/
            System.out.println("开始处理支付成功逻辑");
            String payId = (String)map.get("out_trade_no");
            String priceStr = (String)map.get("total_fee") ;
            BigDecimal price = ((new BigDecimal(priceStr)).divide(new BigDecimal(100.0)));
            System.out.println(payId);
            System.out.println(price);
            Wxpay wxpay = wxpayService.selectById(payId);
            if(wxpay != null){
                if(wxpay.getPayStatus() == 0){
                    wxpay.setPayPrice(price);
                    wxpay.setPayStatus(1);
                    wxpayService.updateWxpay(wxpay);
                    resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                            + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
                    System.out.println(resXml);
                    System.out.println("微信支付回调数据结束");

                    BufferedOutputStream out = new BufferedOutputStream(
                            response.getOutputStream());
                    out.write(resXml.getBytes());
                    out.flush();
                    out.close();
//                    orderService.wxNotify(wxpay);
                }
            }else{
                System.out.println("没有处理的报文：时间-" + new Date() + "内容" + notityXml);
            }

            System.out.println("完成处理支付成功逻辑");
            /**此处添加自己的业务逻辑代码end**/

            //通知微信服务器已经支付成功
            resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                    + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
//            }
        }else{
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            System.out.println(resXml);
            System.out.println("微信支付回调数据结束");

            BufferedOutputStream out = new BufferedOutputStream(
                    response.getOutputStream());
            out.write(resXml.getBytes());
            out.flush();
            out.close();
        }

    }


}
