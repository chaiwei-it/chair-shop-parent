package com.mood.recommendClass.service.impl;

import com.mood.Shift;
import com.mood.StatusCode;
import com.mood.base.Pager;
import com.mood.entity.recommend.RecommendClass;
import com.mood.entity.recommend.request.*;
import com.mood.entity.recommend.response.*;
import com.mood.recommendClass.dao.RecommendClassDao;
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
public class RecommendClassService implements com.mood.recommendClass.service.RecommendClassService {

    @Autowired
    private RecommendClassDao recommendClassDao;


    @Override
    public int insert(RecommendClass recommendClass) {
        recommendClass.setId(IdGen.uuid());
        recommendClass.setCreateTime(System.currentTimeMillis());
        recommendClass.setUpdateTime(System.currentTimeMillis());
        recommendClass.setDelStatus(0);
        return recommendClassDao.insert(recommendClass);
    }

    @Override
    public int update(RecommendClass recommendClass) {
        recommendClass.setUpdateTime(System.currentTimeMillis());
        return recommendClassDao.update(recommendClass);
    }

    @Override
    public int deleteById(String id) {
        return recommendClassDao.deleteById(id);
    }

    @Override
    public RecommendClass selectById(String id) {
        return recommendClassDao.selectById(id);
    }

    @Override
    public List<RecommendClass> selectAll(JSONObject param) {
        return recommendClassDao.selectAll(param);
    }

    @Override
    public Pager<RecommendClass> selectPager(Pager pager){
        return recommendClassDao.selectPager(pager);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RecommendClassInsertResponse insert(RecommendClassInsertRequest request){
        RecommendClass recommendClass = OrikaMapper.map(request, RecommendClass.class);
        recommendClass.setId(IdGen.uuid());
        recommendClassDao.insert(recommendClass);
        return OrikaMapper.map(recommendClass, RecommendClassInsertResponse.class);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RecommendClassUpdateResponse update(RecommendClassUpdateRequest request){
        RecommendClass recommendClass = OrikaMapper.map(request, RecommendClass.class);
        recommendClassDao.update(recommendClass);
        return OrikaMapper.map(recommendClass, RecommendClassUpdateResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(RecommendClassDeleteRequest request){
        recommendClassDao.deleteById(request.getId());
        Shift.fatal(StatusCode.OK);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RecommendClassSelectResponse select(RecommendClassSelectRequest request){
        RecommendClass recommendClass = recommendClassDao.selectById(request.getId());
        return OrikaMapper.map(recommendClass, RecommendClassSelectResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RecommendClassListResponse selectList(RecommendClassListRequest request){
        RecommendClass recommendClass = OrikaMapper.map(request, RecommendClass.class);
        List<RecommendClass> recommendClassList = recommendClassDao.selectAll(recommendClass);
        List<RecommendClassDetailsResponse> detailsList = OrikaMapper.mapList(recommendClassList, RecommendClassDetailsResponse.class);
        RecommendClassListResponse response = new RecommendClassListResponse();
        response.setList(detailsList);
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RecommendClassPagerResponse selectPager(RecommendClassPagerRequest request){
        RecommendClass recommendClass = OrikaMapper.map(request, RecommendClass.class);
        Pager<RecommendClass> pager = OrikaMapper.map(request, Pager.class);
        pager.setObject(recommendClass);
        pager = recommendClassDao.selectPager(pager);
        List<RecommendClassDetailsResponse> detailsList = OrikaMapper.mapList(pager.getData(), RecommendClassDetailsResponse.class);
        RecommendClassPagerResponse response = OrikaMapper.map(pager, RecommendClassPagerResponse.class);
        response.setList(detailsList);
        return response;
    }

}
