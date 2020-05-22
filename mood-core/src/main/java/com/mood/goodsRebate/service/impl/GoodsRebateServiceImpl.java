package com.mood.goodsRebate.service.impl;

import com.mood.base.Pager;
import com.mood.entity.goodsRebate.GoodsRebate;
import com.mood.goodsRebate.dao.GoodsRebateDao;
import com.mood.goodsRebate.service.GoodsRebateService;
import com.mood.utils.IdGen;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
@Service
public class GoodsRebateServiceImpl implements GoodsRebateService {

    @Autowired
    private GoodsRebateDao goodsRebateDao;


    @Override
    public int insert(GoodsRebate goodsRebate) {
        goodsRebate.setId(IdGen.uuid());
        return goodsRebateDao.insert(goodsRebate);
    }

    @Override
    public int update(GoodsRebate goodsRebate) {
        return goodsRebateDao.update(goodsRebate);
    }

    @Override
    public int deleteById(String id) {
        return goodsRebateDao.deleteById(id);
    }

    @Override
    public GoodsRebate selectById(String id) {
        return goodsRebateDao.selectById(id);
    }

    @Override
    public List<GoodsRebate> selectAll(JSONObject param) {
        return goodsRebateDao.selectAll(param);
    }

    @Override
    public Pager<GoodsRebate> selectPager(Pager pager){
        return goodsRebateDao.selectPager(pager);
    }

    @Override
    public GoodsRebate selectByGoodId(String goodsId){
        JSONObject param = new JSONObject();
        param.put("goodsId", goodsId);
        List<GoodsRebate> goodsRebateList = selectAll(param);
        if(goodsRebateList.size() > 0){
            return goodsRebateList.get(0);
        }
        return new GoodsRebate();
    }

    @Override
    public void deleteByGoodsId(String goodsId){
        JSONObject param = new JSONObject();
        param.put("goodsId", goodsId);
        List<GoodsRebate> goodsRebateList = selectAll(param);
        for (GoodsRebate goodsRebate: goodsRebateList) {
            goodsRebateDao.deleteById(goodsRebate.getId());
        }
    }

}
