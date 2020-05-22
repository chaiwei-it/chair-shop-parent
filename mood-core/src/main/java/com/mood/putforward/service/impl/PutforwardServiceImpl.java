package com.mood.putforward.service.impl;

import com.mood.base.Pager;
import com.mood.entity.putforward.Putforward;
import com.mood.entity.putforward.Putforward;
import com.mood.entity.putforward.request.*;
import com.mood.entity.putforward.response.*;
import com.mood.entity.userRebate.UserRebate;
import com.mood.putforward.dao.PutforwardDao;
import com.mood.putforward.service.PutforwardService;
import com.mood.userRebate.service.UserRebateService;
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
public class PutforwardServiceImpl implements PutforwardService {

    @Autowired
    private PutforwardDao putforwardDao;

    @Autowired
    private UserRebateService userRebateService;


    @Override
    public int insert(Putforward putforward) {
        putforward.setId(IdGen.uuid());
        putforward.setCreateTime(System.currentTimeMillis());
        putforward.setUpdateTime(System.currentTimeMillis());
        putforward.setDelStatus(0);
        UserRebate userRebate = userRebateService.selectById(putforward.getUserId());
        userRebate.setBalance(userRebate.getBalance().subtract(putforward.getPrice()));
        userRebateService.update(userRebate);
        return putforwardDao.insert(putforward);
    }

    @Override
    public int update(Putforward putforward) {
        putforward.setUpdateTime(System.currentTimeMillis());
        return putforwardDao.update(putforward);
    }

    @Override
    public int deleteById(String id) {
        return putforwardDao.deleteById(id);
    }

    @Override
    public Putforward selectById(String id) {
        return putforwardDao.selectById(id);
    }

    @Override
    public List<Putforward> selectAll(JSONObject param) {
        return putforwardDao.selectAll(param);
    }

    @Override
    public Pager<Putforward> selectPager(Pager pager){
        return putforwardDao.selectPager(pager);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PutforwardInsertResponse insert(PutforwardInsertRequest request){
        Putforward putforward = OrikaMapper.map(request, Putforward.class);
        putforward.setId(IdGen.uuid());
        putforward.setCreateTime(System.currentTimeMillis());
        putforwardDao.insert(putforward);
        return OrikaMapper.map(putforward, PutforwardInsertResponse.class);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PutforwardUpdateResponse update(PutforwardUpdateRequest request){
        Putforward putforward = OrikaMapper.map(request, Putforward.class);
        putforwardDao.update(putforward);
        if(putforward.getStatus() == 3){
            putforward = putforwardDao.selectById(putforward.getId());
            UserRebate userRebate = userRebateService.selectById(putforward.getUserId());
            userRebate.setBalance(userRebate.getBalance().add(putforward.getPrice()));
            userRebateService.update(userRebate);
        }
        return OrikaMapper.map(putforward, PutforwardUpdateResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PutforwardDeleteResponse delete(PutforwardDeleteRequest request){
        putforwardDao.deleteById(request.getId());
        return new PutforwardDeleteResponse();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PutforwardSelectResponse select(PutforwardSelectRequest request){
        Putforward putforward = putforwardDao.selectById(request.getId());
        return OrikaMapper.map(putforward, PutforwardSelectResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PutforwardListResponse selectList(PutforwardListRequest request){
        Putforward putforward = OrikaMapper.map(request, Putforward.class);
        List<Putforward> putforwardList = putforwardDao.selectAll(putforward);
        List<PutforwardDetailsResponse> detailsList = OrikaMapper.mapList(putforwardList, PutforwardDetailsResponse.class);
        PutforwardListResponse response = new PutforwardListResponse();
        response.setList(detailsList);
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PutforwardPagerResponse selectPager(PutforwardPagerRequest request){
        Putforward putforward = OrikaMapper.map(request, Putforward.class);
        Pager<Putforward> pager = OrikaMapper.map(request, Pager.class);
        pager.setObject(putforward);
        pager = putforwardDao.selectPager(pager);
        List<PutforwardDetailsResponse> detailsList = OrikaMapper.mapList(pager.getData(), PutforwardDetailsResponse.class);
        PutforwardPagerResponse response = OrikaMapper.map(pager, PutforwardPagerResponse.class);
        for(PutforwardDetailsResponse putforwardDetailsResponse: detailsList){
            putforwardDetailsResponse.setUserRebate(userRebateService.selectById(putforwardDetailsResponse.getUserId()));
        }
        response.setList(detailsList);
        return response;
    }

}
