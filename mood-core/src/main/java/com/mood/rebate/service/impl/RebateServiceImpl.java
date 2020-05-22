package com.mood.rebate.service.impl;

import com.mood.Shift;
import com.mood.StatusCode;
import com.mood.address.service.AddressService;
import com.mood.agent.service.AgentService;
import com.mood.base.Pager;
import com.mood.entity.address.Address;
import com.mood.entity.agent.Agent;
import com.mood.entity.goodsRebate.GoodsRebate;
import com.mood.entity.order.Order;
import com.mood.entity.orderAddress.OrderAddress;
import com.mood.entity.orderGoods.OrderGoods;
import com.mood.entity.rabate.*;
import com.mood.entity.userRebate.UserRebate;
import com.mood.goodsRebate.service.GoodsRebateService;
import com.mood.model.response.RestfulResponse;
import com.mood.order.service.OrderService;
import com.mood.orderAddress.service.OrderAddressService;
import com.mood.orderGoods.service.OrderGoodsService;
import com.mood.rebate.dao.RebateDao;
import com.mood.rebate.service.RebateService;
import com.mood.userRebate.service.UserRebateService;
import com.mood.utils.IdGen;
import com.mood.utils.SmsUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 用户模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
@Service
public class RebateServiceImpl implements RebateService {

    @Autowired
    private RebateDao rebateDao;

    @Autowired
    private UserRebateService userRebateService;

    @Autowired
    private AgentService agentService;

    @Autowired
    private OrderGoodsService orderGoodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderAddressService orderAddressService;

    @Autowired
    private GoodsRebateService goodsRebateService;


    @Override
    public int insert(Rebate rebate) {
        rebate.setId(IdGen.uuid());
        rebate.setCreateTime(System.currentTimeMillis());
        rebate.setUpdateTime(System.currentTimeMillis());
        rebate.setDelStatus(0);
        return rebateDao.insert(rebate);
    }

    @Override
    public int update(Rebate rebate) {
        rebate.setUpdateTime(System.currentTimeMillis());
        return rebateDao.update(rebate);
    }

    @Override
    public int deleteById(String id) {
        return rebateDao.deleteById(id);
    }

    @Override
    public Rebate selectById(String id) {
        return rebateDao.selectById(id);
    }

    @Override
    public List<Rebate> selectAll(JSONObject param) {
        return rebateDao.selectAll(param);
    }

    @Override
    public Pager<Rebate> selectPager(Pager pager){
        return rebateDao.selectPager(pager);
    }
    @Override
    public List<Rebate> getRebateList(String rebateNum){
        return rebateDao.getRebateList(rebateNum);
    }

    @Override
    public RestfulResponse rebateByAgent(String agentId){
        if(!agentRebate(agentId)){
            Shift.fatal(StatusCode.USER_NEVER);
        }
        return new RestfulResponse();
    }

    /**
     * 购物返利
     * @param orderId
     * @return
     */
    @Transactional
    @Override
    public Boolean orderRebate(String orderId){
        //获取返利商品金额
        JSONObject param = new JSONObject();
        param.put("orderId",orderId);
        List<OrderGoods> orderGoodsList = orderGoodsService.selectAll(param);
        BigDecimal memberPrice = new BigDecimal(0);
        BigDecimal vipPrice = new BigDecimal(0);
        BigDecimal agentPrice = new BigDecimal(0);
        BigDecimal provincePrice = new BigDecimal(0);
        BigDecimal cityPrice = new BigDecimal(0);
        BigDecimal areaPrice = new BigDecimal(0);
        for(OrderGoods orderGoods: orderGoodsList){
            GoodsRebate goodsRebate = goodsRebateService.selectByGoodId(orderGoods.getGoodsId());
            memberPrice = memberPrice.add(goodsRebate.getMemberPrice().multiply(new BigDecimal(orderGoods.getGoodsNum())));
            vipPrice = vipPrice.add(goodsRebate.getVipPrice().multiply(new BigDecimal(orderGoods.getGoodsNum())));
            agentPrice = agentPrice.add(goodsRebate.getAgentPrice().multiply(new BigDecimal(orderGoods.getGoodsNum())));
            provincePrice = provincePrice.add(goodsRebate.getProvincePrice().multiply(new BigDecimal(orderGoods.getGoodsNum())));
            cityPrice = cityPrice.add(goodsRebate.getCityPrice().multiply(new BigDecimal(orderGoods.getGoodsNum())));
            areaPrice = areaPrice.add(goodsRebate.getAreaPrice().multiply(new BigDecimal(orderGoods.getGoodsNum())));
        }
        Order order = orderService.selectById(orderId);
        //获取上级用户
        UserRebate buyRebate = userRebateService.selectById(order.getBuyerId());
        List<UserRebate> parentUserRebateList = new ArrayList<UserRebate>();
        String parentId =buyRebate.getParentId();
        while(!"0".equals(parentId)){
            UserRebate parentUserRebate = userRebateService.selectById(parentId);
            parentUserRebateList.add(parentUserRebate);
            parentId = parentUserRebate.getParentId();
        }
        //三代返利
        getParent(orderId, buyRebate, parentUserRebateList, memberPrice, vipPrice, agentPrice);
        //区域返利
        getRegion(orderId, buyRebate, provincePrice, cityPrice, areaPrice);
        return true;
    }

    public void getParent(String orderId, UserRebate buyRebate, List<UserRebate> parentUserRebateList, BigDecimal memberPrice, BigDecimal vipPrice, BigDecimal agentPrice){
        List<Rebate> rebateList = new ArrayList<Rebate>();
        //三代内
        Boolean isHaveVIP = false;
        Boolean isHaveAgent = false;
        for(int i = 0; i < 3 && i < parentUserRebateList.size(); i++){
            UserRebate parentUserRebate = parentUserRebateList.get(i);
            Rebate rebate = creatRebate(buyRebate, parentUserRebate, orderId,1, 1);
            rebate.setRabatePrice(memberPrice);
            if(parentUserRebate.getGrade() >= 2 && !isHaveVIP){
                isHaveVIP = true;
                rebate.setRabatePrice(rebate.getRabatePrice().add(vipPrice));
                rebate.setGradeType(2);
            }
            if(parentUserRebate.getGrade() >= 3 && !isHaveAgent){
                isHaveVIP = true;
                isHaveAgent = true;
                rebate.setRabatePrice(rebate.getRabatePrice().add(agentPrice));
                rebate.setGradeType(3);
            }
            rebate.setLevel(i + 1);
            rebateList.add(rebate);
        }
        //三代外
        for(int i = 3; i < parentUserRebateList.size() && !isHaveVIP && !isHaveAgent; i++){
            UserRebate parentUserRebate = parentUserRebateList.get(i);
            if(parentUserRebate.getGrade() == 2 && !isHaveVIP){
                isHaveVIP = true;
                Rebate rebate = creatRebate(buyRebate, parentUserRebate, orderId,1, 1);
                rebate.setRabatePrice(vipPrice);
                rebate.setGradeType(2);
                rebate.setLevel(i + 1);
                rebateList.add(rebate);
            }
            if(parentUserRebate.getGrade() >= 3 && !isHaveAgent){
                isHaveVIP = true;
                isHaveAgent = true;
                Rebate rebate = creatRebate(buyRebate, parentUserRebate, orderId,1, 1);
                rebate.setRabatePrice(vipPrice.add(agentPrice));
                rebate.setGradeType(3);
                rebate.setLevel(i + 1);
                rebateList.add(rebate);
            }
        }
        for (Rebate rebate: rebateList){
            rebate(rebate);
        }

    }

    public void getRegion(String orderId, UserRebate buyRebate, BigDecimal provincePrice, BigDecimal cityPrice, BigDecimal areaPrice){
        JSONObject param = new JSONObject();
        //省级
        OrderAddress address = orderAddressService.selectByOrderId(orderId);
        param.put("provinceId", address.getProvinceId());
        param.put("grade", 6);
        creatRegion(param, orderId, buyRebate, 6, provincePrice);
        //市级
        param.put("cityId", address.getCityId());
        param.put("grade", 5);
        creatRegion(param, orderId, buyRebate, 5, cityPrice);
        //区级
        param.put("areaId", address.getAreaId());
        param.put("grade", 4);
        creatRegion(param, orderId, buyRebate, 4, areaPrice);

    }

    public void creatRegion(JSONObject param, String orderId, UserRebate buyRebate, Integer rebateGrade, BigDecimal price){
        List<Agent> agentList = agentService.selectAll(param);
        if(agentList.size() > 0){
            UserRebate areaUserRebate = userRebateService.selectById(agentList.get(0).getUserId());
            if(areaUserRebate != null){
                Rebate rebate = creatRebate(buyRebate, areaUserRebate, orderId,1, rebateGrade);
                rebate.setRabatePrice(price);
                rebate(rebate);
            }
        }
    }

    /**
     * 升级返利
     * @param
     * @return
     */
    @Override
    public Boolean agentRebate(String agentId){
        //获取上级
        Agent agent = agentService.selectById(agentId);
        UserRebate userRebate = userRebateService.selectById(agent.getUserId());
        List<UserRebate> parentUserRebateList = new ArrayList<UserRebate>();
        String parentId =userRebate.getParentId();
        int num = 1;
        while(!"0".equals(parentId) && num <= 3){
            UserRebate parentUserRebate = userRebateService.selectById(parentId);
            parentUserRebateList.add(parentUserRebate);
            parentId = parentUserRebate.getParentId();
            if(parentUserRebateList.size() >= 3){
                break;
            }
        }
        BigDecimal[] price = {new BigDecimal(1000),new BigDecimal(500),new BigDecimal(500)};
        BigDecimal[] vipPrice = {new BigDecimal(100),new BigDecimal(50),new BigDecimal(50)};
        int pNum = 0;
        for(UserRebate pUserRebate:parentUserRebateList){
            Rebate rebate = new Rebate();
            rebate.setId(IdGen.uuid());
            rebate.setBuyId(userRebate.getId());
            rebate.setBuyName(userRebate.getUsername());
            rebate.setBuyNum(userRebate.getUserNum());
            rebate.setUserId(pUserRebate.getId());
            rebate.setUserName(pUserRebate.getUsername());
            rebate.setUserNum(pUserRebate.getUserNum());
            rebate.setOrderId(agentId);
            rebate.setGradeType(agent.getGrade());
            if(agent.getGrade() == 2){
                rebate.setRabatePrice(vipPrice[pNum++]);
            }else if(agent.getGrade() == 3){
                rebate.setRabatePrice(price[pNum++]);
            }else if(agent.getGrade() == 4){

            }else if(agent.getGrade() == 5){

            }else if(agent.getGrade() == 6){

            }
            Date d = new Date();
            System.out.println(d);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateNowStr = sdf.format(d);
            rebate.setCreateData(dateNowStr);
            rebate.setLevel(pNum);
            rebate.setRebateType(2);
            rebate(rebate);
        }
        return true;
    }

    public Rebate creatRebate(UserRebate buyRebate, UserRebate userRebate, String orderId, Integer rebateType, Integer rebateGrade){
        Rebate rebate = new Rebate();
        rebate.setId(IdGen.uuid());
        rebate.setBuyId(buyRebate.getId());
        rebate.setBuyName(buyRebate.getUsername());
        rebate.setBuyNum(buyRebate.getUserNum());
        rebate.setUserId(userRebate.getId());
        rebate.setUserName(userRebate.getUsername());
        rebate.setUserNum(userRebate.getUserNum());
        rebate.setOrderId(orderId);
        rebate.setRebateType(rebateType);
        Date d = new Date();
        System.out.println(d);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(d);
        rebate.setCreateData(dateNowStr);
        rebate.setGradeType(rebateGrade);
        rebate.setRebateType(1);
        return rebate;
    }


    @Override
    public void rebate(Rebate rebate){
        UserRebate userRebate = userRebateService.selectById(rebate.getUserId());
        Date d = new Date();
        System.out.println(d);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(d);
        rebate.setRabateData(dateNowStr);
        rebate.setStatus(1);
        rebate.setUpdateTime(System.currentTimeMillis());
        rebateDao.insert(rebate);
        if (userRebate.getMobile() != null && !"".equals(userRebate.getMobile())) {
            if (rebate.getRebateType() == 1) {
                if (userRebate.getGrade() == 0) {
                    String message = "{\"name\":\"" + rebate.getBuyName() + "\", \"num\":\"" + rebate.getBuyNum() + "\", \"price\":\"" + rebate.getRabatePrice() + "\"}";
                    SmsUtil.sendSms(userRebate.getMobile(), "SMS_139231443", message);
                    rebateDao.deleteById(rebate.getId());
                } else if (userRebate.getGrade() == 1) {
                    userRebate.setBalance(userRebate.getBalance().add(rebate.getRabatePrice()));
                    userRebateService.update(userRebate);
                    String message = "{\"name\":\"" + rebate.getBuyName() + "\", \"num\":\"" + rebate.getBuyNum() + "\", \"price\":\"" + rebate.getRabatePrice() + "\"}";
                    SmsUtil.sendSms(userRebate.getMobile(), "SMS_139236495", message);
                }else if (userRebate.getGrade() == 2) {
                    userRebate.setBalance(userRebate.getBalance().add(rebate.getRabatePrice()));
                    userRebateService.update(userRebate);
                    String message = "{\"name\":\"" + rebate.getBuyName() + "\", \"num\":\"" + rebate.getBuyNum() + "\", \"price\":\"" + rebate.getRabatePrice() + "\"}";
                    SmsUtil.sendSms(userRebate.getMobile(), "SMS_139226548", message);
                }else if (userRebate.getGrade() >= 3) {
                    userRebate.setBalance(userRebate.getBalance().add(rebate.getRabatePrice()));
                    userRebateService.update(userRebate);
                }
            }else if(rebate.getRebateType() == 2){
                if (userRebate.getGrade() == 0 || userRebate.getGrade() == 1) {
                    if(rebate.getGradeType() == 2){
                        String message = "{\"name\":\"" + rebate.getBuyName() + "\", \"num\":\"" + rebate.getBuyNum() + "\", \"price\":\"" + rebate.getRabatePrice() + "\"}";
                        SmsUtil.sendSms(userRebate.getMobile(), "SMS_139226549", message);
                        rebateDao.deleteById(rebate.getId());
                    }else if(rebate.getGradeType() == 3){
                        String message = "{\"name\":\"" + rebate.getBuyName() + "\", \"num\":\"" + rebate.getBuyNum() + "\", \"price\":\"" + rebate.getRabatePrice() + "\"}";
                        SmsUtil.sendSms(userRebate.getMobile(), "SMS_139236496", message);
                        rebateDao.deleteById(rebate.getId());
                    }
                } else if (userRebate.getGrade() == 2) {
                    if(rebate.getGradeType() == 2){
                        userRebate.setBalance(userRebate.getBalance().add(rebate.getRabatePrice()));
                        userRebateService.update(userRebate);
                        String message = "{\"name\":\"" + rebate.getBuyName() + "\", \"num\":\"" + rebate.getBuyNum() + "\", \"price\":\"" + rebate.getRabatePrice() + "\"}";
                        SmsUtil.sendSms(userRebate.getMobile(), "SMS_139236497", message);
                    }else if(rebate.getGradeType() == 3){
                        String message = "{\"name\":\"" + rebate.getBuyName() + "\", \"num\":\"" + rebate.getBuyNum() + "\", \"price\":\"" + rebate.getRabatePrice() + "\"}";
                        SmsUtil.sendSms(userRebate.getMobile(), "SMS_139226552", message);
                        rebateDao.deleteById(rebate.getId());
                    }
                } else if(userRebate.getGrade() >= 3){
                    if(userRebate.getGrade() >= rebate.getGradeType()){
                        userRebate.setBalance(userRebate.getBalance().add(rebate.getRabatePrice()));
                        userRebateService.update(userRebate);
                    }else{
                        rebateDao.deleteById(rebate.getId());
                    }
                }

            }
        }
    }

}
