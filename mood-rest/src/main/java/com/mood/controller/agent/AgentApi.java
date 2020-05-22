package com.mood.controller.agent;

import com.mood.base.BaseController;
import com.mood.base.Pager;
import com.mood.common.HttpCode;
import com.mood.entity.agent.Agent;
import com.mood.agent.service.AgentService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * 模块
 * @author chaiwei
 * @time 2018-05-15 下午10:00
 */
@RestController
@RequestMapping("/api/{version}/agent")
public class AgentApi extends BaseController {

    @Autowired
    private AgentService agentService;

    /**
     * 添加
     * @param
     * @return
     */
    @PostMapping("")
    public ResponseEntity<ModelMap> create(
            @RequestParam(value = "username", required = false, defaultValue = "") String username,
            @RequestParam(value = "mobile", required = false, defaultValue = "") String mobile,
            @RequestParam(value = "cardNum", required = false, defaultValue = "") String cardNum,
            @RequestParam(value = "province", required = false, defaultValue = "") String province,
            @RequestParam(value = "city", required = false, defaultValue = "") String city,
            @RequestParam(value = "area", required = false, defaultValue = "") String area,
            @RequestParam(value = "provinceId", required = false, defaultValue = "") Integer provinceId,
            @RequestParam(value = "cityId", required = false, defaultValue = "") Integer cityId,
            @RequestParam(value = "areaId", required = false, defaultValue = "") Integer areaId,
            @RequestParam(value = "details", required = false, defaultValue = "") String details,
            @RequestParam(value = "grade", required = false, defaultValue = "1") Integer grade,
            ModelMap modelMap, HttpServletRequest request){
        String userId = getUserId(request);
        if(userId == null){
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        }
        Agent agent = new Agent();
        agent.setUserId(userId);
        agent.setUsername(username);
        agent.setMobile(mobile);
        agent.setCardNum(cardNum);
        agent.setProvince(province);
        agent.setCity(city);
        agent.setArea(area);
        agent.setProvinceId(provinceId);
        agent.setCityId(cityId);
        agent.setAreaId(areaId);
        agent.setDetails(details);
        agent.setGrade(grade);
        agent.setPayStatus(0);
        agent.setAgentStatus(1);
        if(grade == 2){
            agent.setPrice(new BigDecimal(499));
//            agent.setPrice(new BigDecimal(0.01));
        }else if(grade == 3){
            agent.setPrice(new BigDecimal(4999));
//            agent.setPrice(new BigDecimal(0.01));
        }
        Integer result = agentService.insert(agent);
        if(result > 0){
            return setSuccessModelMap(modelMap,agent);
        }
        return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * 修改
     * @param
     * @return
     */
    @PutMapping("")
    public ResponseEntity<ModelMap> update(@RequestParam(value = "id", required = false, defaultValue = "") String id,
                                           @RequestParam(value = "username", required = false, defaultValue = "") String username,
                                           @RequestParam(value = "mobile", required = false, defaultValue = "") String mobile,
                                           @RequestParam(value = "cardNum", required = false, defaultValue = "") String cardNum,
                                           @RequestParam(value = "province", required = false, defaultValue = "") String province,
                                           @RequestParam(value = "city", required = false, defaultValue = "") String city,
                                           @RequestParam(value = "area", required = false, defaultValue = "") String area,
                                           @RequestParam(value = "provinceId", required = false, defaultValue = "") Integer provinceId,
                                           @RequestParam(value = "cityId", required = false, defaultValue = "") Integer cityId,
                                           @RequestParam(value = "areaId", required = false, defaultValue = "") Integer areaId,
                                           @RequestParam(value = "details", required = false, defaultValue = "") String details,
                                           @RequestParam(value = "grade", required = false, defaultValue = "1") Integer grade,
                          ModelMap modelMap, HttpServletRequest request){
        String userId = getUserId(request);
        if(userId == null){
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        }
        Agent agent = new Agent();
        agent.setId(id);
        agent.setUserId(userId);
        agent.setUsername(username);
        agent.setMobile(mobile);
        agent.setCardNum(cardNum);
        agent.setProvince(province);
        agent.setCity(city);
        agent.setArea(area);
        agent.setProvinceId(provinceId);
        agent.setCityId(cityId);
        agent.setAreaId(areaId);
        agent.setDetails(details);
        agent.setGrade(grade);
//        agent.setPayStatus(0);
//        agent.setAgentStatus(1);
        if(grade == 2){
            agent.setPrice(new BigDecimal(499));
//            agent.setPrice(new BigDecimal(0.01));
        }else if(grade == 3){
            agent.setPrice(new BigDecimal(4999));
//            agent.setPrice(new BigDecimal(0.01));
        }
        Integer result = agentService.update(agent);
        if(result > 0){
            return setSuccessModelMap(modelMap,agent);
        }
        return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ModelMap> delete(@PathVariable(value = "id", required = false) String id,
                          ModelMap modelMap, HttpServletRequest request){
        Integer result = agentService.deleteById(id);
        if(result > 0){
            return setSuccessModelMap(modelMap);
        }
        return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * 详情
     * @param
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<ModelMap> select(
            @PathVariable(value = "id", required = false) String id,
            ModelMap modelMap, HttpServletRequest request){
        Agent agent = agentService.selectById(id);
        return setSuccessModelMap(modelMap, agent);
    }

    /**
     * 详情
     * @param
     * @return
     */
    @GetMapping("selfDetails")
    public ResponseEntity<ModelMap> selfDetails(
            @RequestParam(value = "grade", required = false, defaultValue = "1") Integer grade,
                             ModelMap modelMap, HttpServletRequest request){
        String userId = getUserId(request);
        if(userId == null){
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        }
        JSONObject param = new JSONObject();
        param.put("userId",userId);
        param.put("grade",grade);
        List<Agent> list = agentService.selectAll(param);
        if(list.size() > 0){
            return setSuccessModelMap(modelMap,list.get(0));
        }
        return setSuccessModelMap(modelMap,new Agent());
    }

    /**
     * 列表
     * @param agentType
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<ModelMap> list(@RequestParam(value = "agentType", required = false, defaultValue = "") String agentType,
                           ModelMap modelMap, HttpServletRequest request){
        JSONObject param = new JSONObject();
        if(!"".equals(agentType)){
            param.put("agentType",agentType);
        }
        List<Agent> list = agentService.selectAll(param);
        return setSuccessModelMap(modelMap,list);
    }

    /**
     * 分页
     * @param pageIndex
     * @param pageSize
     * @param agentName
     * @return
     */
    @GetMapping("pager")
    public ResponseEntity<ModelMap> pager(@RequestParam(value = "pageIndex",required = false,  defaultValue = "1") Integer pageIndex,
                                          @RequestParam(value = "pageSize",required = false,  defaultValue = "20") Integer pageSize,
                                          @RequestParam(value = "agentName", required = false, defaultValue = "") String agentName,
                                          ModelMap modelMap, HttpServletRequest request){
        Pager<Agent> pager = new Pager<Agent>(pageIndex, pageSize);
        JSONObject param = new JSONObject();
        if("".equals(agentName)){
            param.put("agentName",agentName);
        }
        pager.setParams(param);
        pager = agentService.selectPager(pager);
        return setSuccessModelMap(modelMap,pager);
    }

    /**
     * 列表
     * @param id
     * @return
     */
//    @PostMapping("pay")
//    public ResponseEntity<ModelMap> pay(@RequestParam(value = "id", required = false, defaultValue = "") String id,
//                                        @RequestParam(value = "price", required = false, defaultValue = "") Double price,
//                                         ModelMap modelMap, HttpServletRequest request){
//        Member user = getLoginUser(request);
//
//        if(user == null){
//            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
//        }
//        Agent agent = agentService.selectById(id);
//        agent.setPayId(IdGen.uuid());
//        String spbill_create_ip = IpUtils.getIpAddr(request);
//        Json json = wxPay(agent.getPayId() , price, user.getMemberOpenid(), spbill_create_ip);
//        if(json.isSuccess()){
//            JSONObject jsonObject = JSONObject.fromObject(json.getData());
//            String packageStr = jsonObject.getString("package");
//            agent.setPrepayId(packageStr.split("=")[1]);
//            agent.setTimeStamp(jsonObject.getString("timeStamp"));
//            agent.setPaySign(jsonObject.getString("paySign"));
//            agent.setAppid(jsonObject.getString("appid"));
//            agent.setNonceStr(jsonObject.getString("nonceStr"));
//            agentService.update(agent);
//            return setSuccessModelMap(modelMap,agent);
//        }else{
//            return setModelMap(modelMap, HttpCode.ORDER_WX_FAIL);
//        }
//    }

    /**
     * @Description: 统一下单
     * @param
     * @param
     * @author: wcf
     * @date: 2017年8月28日
     */
//    public Json wxPay(String orderId, Double price, String openid, String spbill_create_ip){
//        Json json = new Json();
//        try{
//            //生成的随机字符串
//            String nonce_str = StringUtils.getRandomStringByLength(32);
//            //商品名称
//            String body = "济宁市颂妆生物科技有限公司";
//
//
////            String orderNo = "mood123456789";
//            String money = new Integer((int)Math.floor(price.doubleValue() * 100)).toString();//支付金额，单位：分，这边需要转成字符串类型，否则后面的签名会失败
//
//            Map<String, String> packageParams = new HashMap<String, String>();
//            packageParams.put("appid", WxPayConfig.appid);
//            packageParams.put("mch_id", WxPayConfig.mch_id);
//            packageParams.put("nonce_str", nonce_str);
//            packageParams.put("body", body);
//            packageParams.put("out_trade_no", orderId);//商户订单号
//            packageParams.put("total_fee", money);//支付金额，这边需要转成字符串类型，否则后面的签名会失败
//            packageParams.put("spbill_create_ip", spbill_create_ip);
//            packageParams.put("notify_url", WxPayConfig.notify_url);
//            packageParams.put("trade_type", WxPayConfig.TRADETYPE);
//            packageParams.put("openid", openid);
//
//            // 除去数组中的空值和签名参数
//            packageParams = PayUtil.paraFilter(packageParams);
//            String prestr = PayUtil.createLinkString(packageParams); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
//
//            //MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
//            String mysign = PayUtil.sign(prestr, WxPayConfig.key, "utf-8").toUpperCase();
//            System.out.println("=======================第一次签名：" + mysign + "=====================");
//
//            //拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
//            String xml = "<xml>" + "<appid>" + WxPayConfig.appid + "</appid>"
//                    + "<body><![CDATA[" + body + "]]></body>"
//                    + "<mch_id>" + WxPayConfig.mch_id + "</mch_id>"
//                    + "<nonce_str>" + nonce_str + "</nonce_str>"
//                    + "<notify_url>" + WxPayConfig.notify_url + "</notify_url>"
//                    + "<openid>" + openid + "</openid>"
//                    + "<out_trade_no>" + orderId + "</out_trade_no>"
//                    + "<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>"
//                    + "<total_fee>" + money + "</total_fee>"
//                    + "<trade_type>" + WxPayConfig.TRADETYPE + "</trade_type>"
//                    + "<sign>" + mysign + "</sign>"
//                    + "</xml>";
//
//            System.out.println("调试模式_统一下单接口 请求XML数据：" + xml);
//
//            //调用统一下单接口，并接受返回的结果
//            String result = PayUtil.httpRequest(WxPayConfig.pay_url, "POST", xml);
//
//            System.out.println("调试模式_统一下单接口 返回XML数据：" + result);
//
//            // 将解析结果存储在HashMap中
//            Map map = PayUtil.doXMLParse(result);
//
//            String return_code = (String) map.get("return_code");//返回状态码
//
//            //返回给移动端需要的参数
//            Map<String, Object> response = new HashMap<String, Object>();
//            if(return_code == "SUCCESS" || return_code.equals(return_code)){
//                // 业务结果
//                String prepay_id = (String) map.get("prepay_id");//返回的预付单信息
//                response.put("nonceStr", nonce_str);
//                response.put("package", "prepay_id=" + prepay_id);
//                Long timeStamp = System.currentTimeMillis() / 1000;
//                response.put("timeStamp", timeStamp + "");//这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误
//
//                String stringSignTemp = "appId=" + WxPayConfig.appid + "&nonceStr=" + nonce_str + "&package=prepay_id=" + prepay_id+ "&signType=" + WxPayConfig.SIGNTYPE + "&timeStamp=" + timeStamp;
//                //再次签名，这个签名用于小程序端调用wx.requesetPayment方法
//                String paySign = PayUtil.sign(stringSignTemp, WxPayConfig.key, "utf-8").toUpperCase();
//                System.out.println("=======================第二次签名：" + paySign + "=====================");
//                response.put("paySign", paySign);
//
//                //更新订单信息
//                //业务逻辑代码
//            }
//
//            response.put("appid", WxPayConfig.appid);
//
//            json.setSuccess(true);
//            json.setData(response);
//        }catch(Exception e){
//            e.printStackTrace();
//            json.setSuccess(false);
//            json.setMsg("发起失败");
//        }
//        return json;
//    }



}
