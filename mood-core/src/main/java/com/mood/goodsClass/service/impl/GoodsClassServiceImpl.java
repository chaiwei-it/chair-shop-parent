package com.mood.goodsClass.service.impl;

import com.mood.Shift;
import com.mood.StatusCode;
import com.mood.base.Pager;
import com.mood.entity.goodsClass.GoodsClass;
import com.mood.entity.goodsClass.request.*;
import com.mood.entity.goodsClass.response.*;
import com.mood.goodsClass.dao.GoodsClassDao;
import com.mood.goodsClass.service.GoodsClassService;
import com.mood.utils.IdGen;
import com.mood.utils.OrikaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
@Service
public class GoodsClassServiceImpl implements GoodsClassService {

    @Autowired
    private GoodsClassDao goodsClassDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public GoodsClass selectById(String id){
        return goodsClassDao.selectById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public GoodsClassInsertResponse insert(GoodsClassInsertRequest request){
        GoodsClass goodsClass = OrikaMapper.map(request, GoodsClass.class);
        goodsClass.setId(IdGen.uuid());
        goodsClass.setCreateTime(System.currentTimeMillis());
        goodsClass.setUpdateTime(System.currentTimeMillis());
        goodsClassDao.insert(goodsClass);
        return OrikaMapper.map(goodsClass, GoodsClassInsertResponse.class);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public GoodsClassUpdateResponse update(GoodsClassUpdateRequest request){
        GoodsClass goodsClass = OrikaMapper.map(request, GoodsClass.class);
        goodsClassDao.update(goodsClass);
        return OrikaMapper.map(goodsClass, GoodsClassUpdateResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public GoodsClassDeleteResponse delete(GoodsClassDeleteRequest request){
        goodsClassDao.deleteById(request.getId());
        return new GoodsClassDeleteResponse();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public GoodsClassSelectResponse select(GoodsClassSelectRequest request){
        GoodsClass goodsClass = goodsClassDao.selectById(request.getId());
        return OrikaMapper.map(goodsClass, GoodsClassSelectResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public GoodsClassListResponse selectList(GoodsClassListRequest request){
        GoodsClass goodsClass = OrikaMapper.map(request, GoodsClass.class);
        List<GoodsClass> goodsClassList = goodsClassDao.selectAll(goodsClass);
        List<GoodsClassDetailsResponse> detailsList = OrikaMapper.mapList(goodsClassList, GoodsClassDetailsResponse.class);
        GoodsClassListResponse response = new GoodsClassListResponse();
        response.setList(detailsList);
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public GoodsClassPagerResponse selectPager(GoodsClassPagerRequest request){
        GoodsClass goodsClass = OrikaMapper.map(request, GoodsClass.class);
        Pager<GoodsClass> pager = OrikaMapper.map(request, Pager.class);
        pager.setObject(goodsClass);
        pager = goodsClassDao.selectPager(pager);
        List<GoodsClassDetailsResponse> detailsList = OrikaMapper.mapList(pager.getData(), GoodsClassDetailsResponse.class);
        GoodsClassPagerResponse response = OrikaMapper.map(pager, GoodsClassPagerResponse.class);
        response.setList(detailsList);
        return response;
    }
}
