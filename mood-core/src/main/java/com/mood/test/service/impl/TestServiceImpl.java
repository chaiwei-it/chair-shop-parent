package com.mood.test.service.impl;

import com.mood.Shift;
import com.mood.StatusCode;
import com.mood.base.Pager;
import com.mood.entity.test.Test;
import com.mood.entity.test.request.*;
import com.mood.entity.test.response.*;
import com.mood.test.dao.TestDao;
import com.mood.test.service.TestService;
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
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public TestInsertResponse insert(TestInsertRequest request){
        Test test = OrikaMapper.map(request, Test.class);
        test.setId(IdGen.uuid());
        testDao.insert(test);
        return OrikaMapper.map(test, TestInsertResponse.class);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TestUpdateResponse update(TestUpdateRequest request){
        Test test = OrikaMapper.map(request, Test.class);
        testDao.update(test);
        return OrikaMapper.map(test, TestUpdateResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(TestDeleteRequest request){
        testDao.deleteById(request.getId());
        Shift.fatal(StatusCode.OK);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TestSelectResponse select(TestSelectRequest request){
        Test test = testDao.selectById(request.getId());
        return OrikaMapper.map(test, TestSelectResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TestListResponse selectList(TestListRequest request){
        Test test = OrikaMapper.map(request, Test.class);
        List<Test> testList = testDao.selectAll(test);
        List<TestDetailsResponse> detailsList = OrikaMapper.mapList(testList, TestDetailsResponse.class);
        TestListResponse response = new TestListResponse();
        response.setList(detailsList);
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TestPagerResponse selectPager(TestPagerRequest request){
        Test test = OrikaMapper.map(request, Test.class);
        Pager<Test> pager = OrikaMapper.map(request, Pager.class);
        pager.setObject(test);
        pager = testDao.selectPager(pager);
        List<TestDetailsResponse> detailsList = OrikaMapper.mapList(pager.getData(), TestDetailsResponse.class);
        TestPagerResponse response = OrikaMapper.map(pager, TestPagerResponse.class);
        response.setList(detailsList);
        return response;
    }
}
