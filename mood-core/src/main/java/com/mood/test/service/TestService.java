package com.mood.test.service;

import com.mood.entity.test.request.TestDeleteRequest;
import com.mood.entity.test.request.TestInsertRequest;
import com.mood.entity.test.request.TestListRequest;
import com.mood.entity.test.request.TestPagerRequest;
import com.mood.entity.test.request.TestSelectRequest;
import com.mood.entity.test.request.TestUpdateRequest;
import com.mood.entity.test.response.TestInsertResponse;
import com.mood.entity.test.response.TestListResponse;
import com.mood.entity.test.response.TestPagerResponse;
import com.mood.entity.test.response.TestSelectResponse;
import com.mood.entity.test.response.TestUpdateResponse;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface TestService {

    public TestInsertResponse insert(TestInsertRequest request);

    public TestUpdateResponse update(TestUpdateRequest request);

    public void delete(TestDeleteRequest request);

    public TestSelectResponse select(TestSelectRequest request);

    public TestListResponse selectList(TestListRequest request);

    public TestPagerResponse selectPager(TestPagerRequest request);
}
