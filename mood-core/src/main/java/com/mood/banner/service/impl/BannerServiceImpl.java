package com.mood.banner.service.impl;

import com.mood.banner.dao.BannerDao;
import com.mood.banner.service.BannerService;
import com.mood.base.Pager;
import com.mood.entity.banner.Banner;
import com.mood.entity.banner.request.*;
import com.mood.entity.banner.response.*;
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
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerDao bannerDao;


    @Override
    public int insert(Banner banner) {
        banner.setId(IdGen.uuid());
        banner.setCreateTime(System.currentTimeMillis());
        banner.setUpdateTime(System.currentTimeMillis());
        banner.setDelStatus(0);
        return bannerDao.insert(banner);
    }

    @Override
    public int update(Banner banner) {
        banner.setUpdateTime(System.currentTimeMillis());
        return bannerDao.update(banner);
    }

    @Override
    public int deleteById(String id) {
        return bannerDao.deleteById(id);
    }

    @Override
    public Banner selectById(String id) {
        return bannerDao.selectById(id);
    }

    @Override
    public List<Banner> selectAll(JSONObject param) {
        return bannerDao.selectAll(param);
    }

    @Override
    public Pager<Banner> selectPager(Pager pager){
        return bannerDao.selectPager(pager);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BannerInsertResponse insert(BannerInsertRequest request){
        Banner banner = OrikaMapper.map(request, Banner.class);
        banner.setId(IdGen.uuid());
        bannerDao.insert(banner);
        return OrikaMapper.map(banner, BannerInsertResponse.class);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BannerUpdateResponse update(BannerUpdateRequest request){
        Banner banner = OrikaMapper.map(request, Banner.class);
        bannerDao.update(banner);
        return OrikaMapper.map(banner, BannerUpdateResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BannerDeleteResponse delete(BannerDeleteRequest request){
        bannerDao.deleteById(request.getId());
        return new BannerDeleteResponse();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BannerSelectResponse select(BannerSelectRequest request){
        Banner banner = bannerDao.selectById(request.getId());
        return OrikaMapper.map(banner, BannerSelectResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BannerListResponse selectList(BannerListRequest request){
        Banner banner = OrikaMapper.map(request, Banner.class);
        List<Banner> bannerList = bannerDao.selectAll(banner);
        List<BannerDetailsResponse> detailsList = OrikaMapper.mapList(bannerList, BannerDetailsResponse.class);
        BannerListResponse response = new BannerListResponse();
        response.setList(detailsList);
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public BannerPagerResponse selectPager(BannerPagerRequest request){
        Banner banner = OrikaMapper.map(request, Banner.class);
        Pager<Banner> pager = OrikaMapper.map(request, Pager.class);
        pager.setObject(banner);
        pager = bannerDao.selectPager(pager);
        List<BannerDetailsResponse> detailsList = OrikaMapper.mapList(pager.getData(), BannerDetailsResponse.class);
        BannerPagerResponse response = OrikaMapper.map(pager, BannerPagerResponse.class);
        response.setList(detailsList);
        return response;
    }

}
