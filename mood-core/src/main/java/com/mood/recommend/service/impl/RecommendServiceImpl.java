package com.mood.recommend.service.impl;

import com.mood.Shift;
import com.mood.StatusCode;
import com.mood.base.Pager;
import com.mood.entity.recommend.Recommend;
import com.mood.entity.recommend.request.*;
import com.mood.entity.recommend.response.*;
import com.mood.recommend.dao.RecommendDao;
import com.mood.recommend.service.RecommendService;
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
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    private RecommendDao bannerDao;


    @Override
    public int insert(Recommend banner) {
        banner.setId(IdGen.uuid());
        banner.setCreateTime(System.currentTimeMillis());
        banner.setUpdateTime(System.currentTimeMillis());
        banner.setDelStatus(0);
        return bannerDao.insert(banner);
    }

    @Override
    public int update(Recommend banner) {
        banner.setUpdateTime(System.currentTimeMillis());
        return bannerDao.update(banner);
    }

    @Override
    public int deleteById(String id) {
        return bannerDao.deleteById(id);
    }

    @Override
    public Recommend selectById(String id) {
        return bannerDao.selectById(id);
    }

    @Override
    public List<Recommend> selectAll(JSONObject param) {
        return bannerDao.selectAll(param);
    }

    @Override
    public Pager<Recommend> selectPager(Pager pager){
        return bannerDao.selectPager(pager);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RecommendInsertResponse insert(RecommendInsertRequest request){
        Recommend banner = OrikaMapper.map(request, Recommend.class);
        banner.setId(IdGen.uuid());
        bannerDao.insert(banner);
        return OrikaMapper.map(banner, RecommendInsertResponse.class);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RecommendUpdateResponse update(RecommendUpdateRequest request){
        Recommend banner = OrikaMapper.map(request, Recommend.class);
        bannerDao.update(banner);
        return OrikaMapper.map(banner, RecommendUpdateResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(RecommendDeleteRequest request){
        bannerDao.deleteById(request.getId());
        Shift.fatal(StatusCode.OK);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RecommendSelectResponse select(RecommendSelectRequest request){
        Recommend banner = bannerDao.selectById(request.getId());
        return OrikaMapper.map(banner, RecommendSelectResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RecommendListResponse selectList(RecommendListRequest request){
        Recommend banner = OrikaMapper.map(request, Recommend.class);
        List<Recommend> bannerList = bannerDao.selectAll(banner);
        List<RecommendDetailsResponse> detailsList = OrikaMapper.mapList(bannerList, RecommendDetailsResponse.class);
        RecommendListResponse response = new RecommendListResponse();
        response.setList(detailsList);
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RecommendPagerResponse selectPager(RecommendPagerRequest request){
        Recommend banner = OrikaMapper.map(request, Recommend.class);
        Pager<Recommend> pager = OrikaMapper.map(request, Pager.class);
        pager.setObject(banner);
        pager = bannerDao.selectPager(pager);
        List<RecommendDetailsResponse> detailsList = OrikaMapper.mapList(pager.getData(), RecommendDetailsResponse.class);
        RecommendPagerResponse response = OrikaMapper.map(pager, RecommendPagerResponse.class);
        response.setList(detailsList);
        return response;
    }

}
