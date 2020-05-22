package com.mood.wxpay.service.impl;

import com.mood.agent.service.AgentService;
import com.mood.base.Pager;
import com.mood.entity.rabate.Wxpay;
import com.mood.order.service.OrderService;
import com.mood.userRebate.service.UserRebateService;
import com.mood.wxpay.dao.WxpayDao;
import com.mood.wxpay.service.WxpayService;
import com.mood.utils.IdGen;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 * 用户模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
@Service
public class WxpayServiceImpl implements WxpayService {

    @Autowired
    private WxpayDao wxpayDao;

    @Autowired
    private AgentService agentService;

    @Autowired
    private OrderService orderService;

    @Override
    public int insert(Wxpay wxpay) {
        wxpay.setCreateTime(System.currentTimeMillis());
        wxpay.setUpdateTime(System.currentTimeMillis());
        wxpay.setDelStatus(0);
        return wxpayDao.insert(wxpay);
    }

    @Override
    public int update(Wxpay wxpay) {
        wxpay.setUpdateTime(System.currentTimeMillis());
        return wxpayDao.update(wxpay);
    }

    @Override
    public int deleteById(String id) {
        return wxpayDao.deleteById(id);
    }

    @Override
    public Wxpay selectById(String id) {
        return wxpayDao.selectById(id);
    }

    @Override
    public List<Wxpay> selectAll(JSONObject param) {
        return wxpayDao.selectAll(param);
    }

    @Override
    public Pager<Wxpay> selectPager(Pager pager){
        return wxpayDao.selectPager(pager);
    }

    @Override
    public Wxpay selectByOrderId(String orderId){
        JSONObject param = new JSONObject();
        param.put("orderId", orderId);
        List<Wxpay> wxpayList = selectAll(param);
        if(wxpayList.size() > 0){
            return wxpayList.get(0);
        }
        return null;
    }

    @Override
    public void updateWxpay(Wxpay wxpay){

        //修改订单状态
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(wxpay.getOrderType() == 1){
                    orderService.payFinish(wxpay);
                }else{
                    agentService.payFinish(wxpay);
                }
            }
        }).start();
    }

}
