package com.mood.advertClassClass.service.impl;

import com.mood.Shift;
import com.mood.StatusCode;
import com.mood.advertClass.dao.AdvertClassDao;
import com.mood.advertClass.service.AdvertClassService;
import com.mood.base.Pager;
import com.mood.entity.advert.AdvertClass;
import com.mood.entity.advert.request.*;
import com.mood.entity.advert.response.*;
import com.mood.utils.IdGen;
import com.mood.utils.OrikaMapper;
import net.sf.json.JSONObject;
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
public class AdvertClassServiceImpl implements AdvertClassService {

    @Autowired
    private AdvertClassDao advertClassDao;


    @Override
    public int insert(AdvertClass advertClass) {
        advertClass.setId(IdGen.uuid());
        advertClass.setCreateTime(System.currentTimeMillis());
        advertClass.setUpdateTime(System.currentTimeMillis());
        advertClass.setDelStatus(0);
        return advertClassDao.insert(advertClass);
    }

    @Override
    public int update(AdvertClass advertClass) {
        advertClass.setUpdateTime(System.currentTimeMillis());
        return advertClassDao.update(advertClass);
    }

    @Override
    public int deleteById(String id) {
        return advertClassDao.deleteById(id);
    }

    @Override
    public AdvertClass selectById(String id) {
        return advertClassDao.selectById(id);
    }

    @Override
    public List<AdvertClass> selectAll(JSONObject param) {
        return advertClassDao.selectAll(param);
    }

    @Override
    public Pager<AdvertClass> selectPager(Pager pager){
        return advertClassDao.selectPager(pager);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AdvertClassInsertResponse insert(AdvertClassInsertRequest request){
        AdvertClass advertClass = OrikaMapper.map(request, AdvertClass.class);
        advertClass.setId(IdGen.uuid());
        advertClassDao.insert(advertClass);
        return OrikaMapper.map(advertClass, AdvertClassInsertResponse.class);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AdvertClassUpdateResponse update(AdvertClassUpdateRequest request){
        AdvertClass advertClass = OrikaMapper.map(request, AdvertClass.class);
        advertClassDao.update(advertClass);
        return OrikaMapper.map(advertClass, AdvertClassUpdateResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(AdvertClassDeleteRequest request){
        advertClassDao.deleteById(request.getId());
        Shift.fatal(StatusCode.OK);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AdvertClassSelectResponse select(AdvertClassSelectRequest request){
        AdvertClass advertClass = advertClassDao.selectById(request.getId());
        return OrikaMapper.map(advertClass, AdvertClassSelectResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AdvertClassListResponse selectList(AdvertClassListRequest request){
        AdvertClass advertClass = OrikaMapper.map(request, AdvertClass.class);
        List<AdvertClass> advertClassList = advertClassDao.selectAll(advertClass);
        List<AdvertClassDetailsResponse> detailsList = OrikaMapper.mapList(advertClassList, AdvertClassDetailsResponse.class);
        AdvertClassListResponse response = new AdvertClassListResponse();
        response.setList(detailsList);
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AdvertClassPagerResponse selectPager(AdvertClassPagerRequest request){
        AdvertClass advertClass = OrikaMapper.map(request, AdvertClass.class);
        Pager<AdvertClass> pager = OrikaMapper.map(request, Pager.class);
        pager.setObject(advertClass);
        pager = advertClassDao.selectPager(pager);
        List<AdvertClassDetailsResponse> detailsList = OrikaMapper.mapList(pager.getData(), AdvertClassDetailsResponse.class);
        AdvertClassPagerResponse response = OrikaMapper.map(pager, AdvertClassPagerResponse.class);
        response.setList(detailsList);
        return response;
    }

}
