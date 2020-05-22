package com.mood.goodsSpec.service.impl;

import com.mood.Shift;
import com.mood.StatusCode;
import com.mood.base.Pager;
import com.mood.entity.goodsRebate.GoodsRebate;
import com.mood.entity.goodsSpec.GoodsSpec;
import com.mood.entity.goodsSpec.request.*;
import com.mood.entity.goodsSpec.response.*;
import com.mood.goodsSpec.dao.GoodsSpecDao;
import com.mood.goodsSpec.service.GoodsSpecService;
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
public class GoodsSpecServiceImpl implements GoodsSpecService {

    @Autowired
    private GoodsSpecDao goodsSpecDao;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean insert(GoodsSpec goodsSpec){
        goodsSpec.setId(IdGen.uuid());
        goodsSpec.setCreateTime(System.currentTimeMillis());
        goodsSpec.setUpdateTime(System.currentTimeMillis());
        goodsSpecDao.insert(goodsSpec);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public GoodsSpecInsertResponse insert(GoodsSpecInsertRequest request){
        GoodsSpec goodsSpec = OrikaMapper.map(request, GoodsSpec.class);
        goodsSpec.setId(IdGen.uuid());
        goodsSpecDao.insert(goodsSpec);
        return OrikaMapper.map(goodsSpec, GoodsSpecInsertResponse.class);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public GoodsSpecUpdateResponse update(GoodsSpecUpdateRequest request){
        GoodsSpec goodsSpec = OrikaMapper.map(request, GoodsSpec.class);
        goodsSpecDao.update(goodsSpec);
        return OrikaMapper.map(goodsSpec, GoodsSpecUpdateResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(GoodsSpecDeleteRequest request){
        goodsSpecDao.deleteById(request.getId());
        Shift.fatal(StatusCode.OK);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteByGoodsId(String goodsId){
        List<GoodsSpec> goodsSpecList = selectByGoodsId(goodsId);
        for (GoodsSpec goodsSpec: goodsSpecList) {
            goodsSpecDao.deleteById(goodsSpec.getId());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public GoodsSpecSelectResponse select(GoodsSpecSelectRequest request){
        GoodsSpec goodsSpec = goodsSpecDao.selectById(request.getId());
        return OrikaMapper.map(goodsSpec, GoodsSpecSelectResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<GoodsSpec> selectByGoodsId(String goodsId){
        GoodsSpec goodsSpec = new GoodsSpec();
        goodsSpec.setGoodsId(goodsId);
        List<GoodsSpec> goodsSpecList = goodsSpecDao.selectAll(goodsSpec);
        return goodsSpecList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public GoodsSpecListResponse selectList(GoodsSpecListRequest request){
        GoodsSpec goodsSpec = OrikaMapper.map(request, GoodsSpec.class);
        List<GoodsSpec> goodsSpecList = goodsSpecDao.selectAll(goodsSpec);
        List<GoodsSpecDetailsResponse> detailsList = OrikaMapper.mapList(goodsSpecList, GoodsSpecDetailsResponse.class);
        GoodsSpecListResponse response = new GoodsSpecListResponse();
        response.setList(detailsList);
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public GoodsSpecPagerResponse selectPager(GoodsSpecPagerRequest request){
        GoodsSpec goodsSpec = OrikaMapper.map(request, GoodsSpec.class);
        Pager<GoodsSpec> pager = OrikaMapper.map(request, Pager.class);
        pager.setObject(goodsSpec);
        pager = goodsSpecDao.selectPager(pager);
        List<GoodsSpecDetailsResponse> detailsList = OrikaMapper.mapList(pager.getData(), GoodsSpecDetailsResponse.class);
        GoodsSpecPagerResponse response = OrikaMapper.map(pager, GoodsSpecPagerResponse.class);
        response.setList(detailsList);
        return response;
    }
}
