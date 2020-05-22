package com.mood.specValue.service.impl;

import com.mood.Shift;
import com.mood.StatusCode;
import com.mood.base.Pager;
import com.mood.entity.specValue.SpecValue;
import com.mood.entity.specValue.request.*;
import com.mood.entity.specValue.response.*;
import com.mood.specValue.dao.SpecValueDao;
import com.mood.specValue.service.SpecValueService;
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
public class SpecValueServiceImpl implements SpecValueService {

    @Autowired
    private SpecValueDao specValueDao;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public SpecValueInsertResponse insert(SpecValueInsertRequest request){
        SpecValue specValue = OrikaMapper.map(request, SpecValue.class);
        specValue.setId(IdGen.uuid());
        specValue.setCreateTime(System.currentTimeMillis());
        specValue.setDelStatus(0);
        specValueDao.insert(specValue);
        return OrikaMapper.map(specValue, SpecValueInsertResponse.class);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SpecValueUpdateResponse update(SpecValueUpdateRequest request){
        SpecValue specValue = OrikaMapper.map(request, SpecValue.class);
        specValueDao.update(specValue);
        return OrikaMapper.map(specValue, SpecValueUpdateResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SpecValueDeleteResponse delete(SpecValueDeleteRequest request){
        specValueDao.deleteById(request.getId());
        return new SpecValueDeleteResponse();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SpecValueSelectResponse select(SpecValueSelectRequest request){
        SpecValue specValue = specValueDao.selectById(request.getId());
        return OrikaMapper.map(specValue, SpecValueSelectResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SpecValueListResponse selectList(SpecValueListRequest request){
        SpecValue specValue = OrikaMapper.map(request, SpecValue.class);
        List<SpecValue> specValueList = specValueDao.selectAll(specValue);
        List<SpecValueDetailsResponse> detailsList = OrikaMapper.mapList(specValueList, SpecValueDetailsResponse.class);
        SpecValueListResponse response = new SpecValueListResponse();
        response.setList(detailsList);
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SpecValuePagerResponse selectPager(SpecValuePagerRequest request){
        SpecValue specValue = OrikaMapper.map(request, SpecValue.class);
        Pager<SpecValue> pager = OrikaMapper.map(request, Pager.class);
        pager.setObject(specValue);
        pager = specValueDao.selectPager(pager);
        List<SpecValueDetailsResponse> detailsList = OrikaMapper.mapList(pager.getData(), SpecValueDetailsResponse.class);
        SpecValuePagerResponse response = OrikaMapper.map(pager, SpecValuePagerResponse.class);
        response.setList(detailsList);
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<SpecValueDetailsResponse> selectBySpecId(String specId){
        SpecValue specValue = new SpecValue();
        specValue.setSpecId(specId);
        List<SpecValue> specValueList = specValueDao.selectAll(specValue);
        List<SpecValueDetailsResponse> detailsList = OrikaMapper.mapList(specValueList, SpecValueDetailsResponse.class);
        return detailsList;
    }
}
