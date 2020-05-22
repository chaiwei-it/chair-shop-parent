package com.mood.userRebate.service;

import com.alibaba.fastjson.JSONObject;
import com.mood.base.service.BaseService;
import com.mood.entity.userRebate.request.*;
import com.mood.entity.userRebate.response.*;
import com.mood.entity.userRebate.UserRebate;

import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface UserRebateService extends BaseService<UserRebate> {

    public UserRebateInsertResponse insert(UserRebateInsertRequest request);

    public UserRebateUpdateResponse update(UserRebateUpdateRequest request);

    public UserRebateDeleteResponse delete(UserRebateDeleteRequest request);

    public UserRebateSelectResponse select(UserRebateSelectRequest request);

    public UserRebateListResponse selectList(UserRebateListRequest request);

    public UserRebatePagerResponse selectPager(UserRebatePagerRequest request);

//    public UserRebate selectByUserId(String userId);

    public UserRebate selectByUserNum(String userNum);

    public List<UserRebate> selectByParentId(String parentId);

    public void updateParent(String userId, String parentId);

    public UserRebate creat(JSONObject userJson, String codeId, Integer appId);

    UserRebate register(String openid, String unionid, Integer appId, String codeId);
}
