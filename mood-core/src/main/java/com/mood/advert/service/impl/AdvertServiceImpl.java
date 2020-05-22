package com.mood.advert.service.impl;

import com.mood.Shift;
import com.mood.StatusCode;
import com.mood.advert.dao.AdvertDao;
import com.mood.advert.service.AdvertService;
import com.mood.base.Pager;
import com.mood.entity.advert.Advert;
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
public class AdvertServiceImpl implements AdvertService {

    @Autowired
    private AdvertDao bannerDao;


    @Override
    public int insert(Advert banner) {
        banner.setId(IdGen.uuid());
        banner.setCreateTime(System.currentTimeMillis());
        banner.setUpdateTime(System.currentTimeMillis());
        banner.setDelStatus(0);
        return bannerDao.insert(banner);
    }

    @Override
    public int update(Advert banner) {
        banner.setUpdateTime(System.currentTimeMillis());
        return bannerDao.update(banner);
    }

    @Override
    public int deleteById(String id) {
        return bannerDao.deleteById(id);
    }

    @Override
    public Advert selectById(String id) {
        return bannerDao.selectById(id);
    }

    @Override
    public List<Advert> selectAll(JSONObject param) {
        return bannerDao.selectAll(param);
    }

    @Override
    public Pager<Advert> selectPager(Pager pager){
        return bannerDao.selectPager(pager);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AdvertInsertResponse insert(AdvertInsertRequest request){
        Advert banner = OrikaMapper.map(request, Advert.class);
        banner.setId(IdGen.uuid());
        bannerDao.insert(banner);
        return OrikaMapper.map(banner, AdvertInsertResponse.class);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AdvertUpdateResponse update(AdvertUpdateRequest request){
        Advert banner = OrikaMapper.map(request, Advert.class);
        bannerDao.update(banner);
        return OrikaMapper.map(banner, AdvertUpdateResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(AdvertDeleteRequest request){
        bannerDao.deleteById(request.getId());
        Shift.fatal(StatusCode.OK);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AdvertSelectResponse select(AdvertSelectRequest request){
        Advert banner = bannerDao.selectById(request.getId());
        return OrikaMapper.map(banner, AdvertSelectResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AdvertListResponse selectList(AdvertListRequest request){
        Advert banner = OrikaMapper.map(request, Advert.class);
        List<Advert> bannerList = bannerDao.selectAll(banner);
        List<AdvertDetailsResponse> detailsList = OrikaMapper.mapList(bannerList, AdvertDetailsResponse.class);
        AdvertListResponse response = new AdvertListResponse();
        response.setList(detailsList);
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AdvertPagerResponse selectPager(AdvertPagerRequest request){
        Advert banner = OrikaMapper.map(request, Advert.class);
        Pager<Advert> pager = OrikaMapper.map(request, Pager.class);
        pager.setObject(banner);
        pager = bannerDao.selectPager(pager);
        List<AdvertDetailsResponse> detailsList = OrikaMapper.mapList(pager.getData(), AdvertDetailsResponse.class);
        AdvertPagerResponse response = OrikaMapper.map(pager, AdvertPagerResponse.class);
        response.setList(detailsList);
        return response;
    }

}
