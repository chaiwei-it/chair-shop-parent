package com.mood.userRebate.service.impl;

import com.mood.Shift;
import com.mood.StatusCode;
import com.mood.base.Pager;
import com.mood.code.service.CodeService;
import com.mood.entity.rabate.Code;
import com.mood.entity.rabate.UserOpen;
import com.mood.entity.userRebate.UserRebate;
import com.mood.entity.userRebate.request.*;
import com.mood.entity.userRebate.response.*;
import com.mood.entity.userRebate.UserRebate;
import com.mood.userOpen.service.UserOpenService;
import com.mood.userRebate.dao.UserRebateDao;
import com.mood.userRebate.service.UserRebateService;
import com.mood.utils.EmojiFilter;
import com.mood.utils.IdGen;
import com.mood.utils.IdUtil;
import com.mood.utils.OrikaMapper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
@Service
public class UserRebateServiceImpl implements UserRebateService {

    @Autowired
    private UserRebateDao userRebateDao;

    @Autowired
    private UserOpenService userOpenService;

    @Autowired
    private CodeService codeService;

    @Override
    public int insert(UserRebate userRebate) {
        userRebate.setId(IdGen.uuid());
        userRebate.setCreateTime(System.currentTimeMillis());
        userRebate.setUpdateTime(System.currentTimeMillis());
        userRebate.setDelStatus(0);
        return userRebateDao.insert(userRebate);
    }

    @Override
    public int update(UserRebate userRebate) {
        userRebate.setUpdateTime(System.currentTimeMillis());
        return userRebateDao.update(userRebate);
    }

    @Override
    public int deleteById(String id) {
        return userRebateDao.deleteById(id);
    }

    @Override
    public UserRebate selectById(String id) {
        return userRebateDao.selectById(id);
    }

    @Override
    public List<UserRebate> selectAll(JSONObject param) {
        return userRebateDao.selectAll(param);
    }

    @Override
    public Pager<UserRebate> selectPager(Pager pager){
        return userRebateDao.selectPager(pager);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserRebateInsertResponse insert(UserRebateInsertRequest request){
        UserRebate userRebate = OrikaMapper.map(request, UserRebate.class);
        userRebate.setId(IdGen.uuid());
        userRebateDao.insert(userRebate);
        return OrikaMapper.map(userRebate, UserRebateInsertResponse.class);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserRebateUpdateResponse update(UserRebateUpdateRequest request){
        UserRebate userRebate = OrikaMapper.map(request, UserRebate.class);
        userRebateDao.update(userRebate);
        //修改父级
        userRebate = userRebateDao.selectById(userRebate.getId());
        if("0".equals(request.getParentNum())){
            updateParent(userRebate.getId(), request.getParentNum());
        }else{
            UserRebate parentUserRebate = selectByUserNum(request.getParentNum());
            if(parentUserRebate == null){
                Shift.fatal(StatusCode.PARENT_NEVER);
            }else if(!parentUserRebate.getId().equals(userRebate.getParentId())){
                //判断原来是否为父子关系
                List<UserRebate> parentUserRebateList = new ArrayList<UserRebate>();
                String parentId =parentUserRebate.getParentId();
                while(!"0".equals(parentId)){
                    UserRebate oldParentUserRebate = selectById(parentId);
                    parentUserRebateList.add(oldParentUserRebate);
                    parentId = oldParentUserRebate.getParentId();
                }
                //将我未来的父级对接到现在的父级
                for(UserRebate oldUserRebate: parentUserRebateList){
                    if(oldUserRebate.getId().equals(userRebate.getId())){
                        updateParent(parentUserRebate.getId(), oldUserRebate.getParentId());
                        break;
                    }
                }
                updateParent(userRebate.getId(), parentUserRebate.getId());
            }
        }
        return OrikaMapper.map(userRebate, UserRebateUpdateResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateParent(String userId, String parentId){
        UserRebate userRebate = selectById(userId);
        if("0".equals(parentId)){
            userRebate.setParentId(parentId);
            userRebate.setLevel(1);
            userRebateDao.update(userRebate);
        }else{
            UserRebate parentUserRebate = selectById(parentId);
            if(parentUserRebate == null){
                Shift.fatal(StatusCode.PARENT_NEVER);
            }
            userRebate.setParentId(parentUserRebate.getId());
            userRebate.setLevel(parentUserRebate.getLevel() + 1);
            userRebateDao.update(userRebate);
            List<UserRebate> userRebateList = selectByParentId(userRebate.getId());
            for(UserRebate childrenUserRebate: userRebateList){
                updateParent(childrenUserRebate.getId(), userRebate.getId());
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserRebateDeleteResponse delete(UserRebateDeleteRequest request){
        userRebateDao.deleteById(request.getId());
        return new UserRebateDeleteResponse();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserRebateSelectResponse select(UserRebateSelectRequest request){
        UserRebate userRebate = userRebateDao.selectById(request.getId());
        UserRebateSelectResponse response = OrikaMapper.map(userRebate, UserRebateSelectResponse.class);
        response.setParent(OrikaMapper.map(selectById(userRebate.getParentId()), UserRebateSelectResponse.class));
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserRebateListResponse selectList(UserRebateListRequest request){
        //获取一级
        List<UserRebate> returnList = new ArrayList<UserRebate>();
        if(request.getParentId() != null && request.getNum() != null){
            List<UserRebate> firstList = new ArrayList<UserRebate>();
            if(request.getNum() >= 1 || request.getNum() == 0){
                firstList = selectByParentId(request.getParentId());
                if(request.getNum() == 0){
                    returnList.addAll(firstList);
                }else {
                    returnList = firstList;
                }
            }
            List<UserRebate> secondList = new ArrayList<UserRebate>();
            if(request.getNum() >= 2 || request.getNum() == 0){
                for (UserRebate UserRebate : firstList) {
                    List<UserRebate> list = selectByParentId(UserRebate.getId());
                    secondList.addAll(list);
                }
                if(request.getNum() == 0){
                    returnList.addAll(secondList);
                }else {
                    returnList = secondList;
                }
            }
            List<UserRebate> thirdList = new ArrayList<UserRebate>();
            if(request.getNum() == 3 || request.getNum() == 0){
                for (UserRebate UserRebate : secondList) {
                    List<UserRebate> list = selectByParentId(UserRebate.getId());
                    thirdList.addAll(list);
                }
                if(request.getNum() == 0){
                    returnList.addAll(thirdList);
                }else {
                    returnList = thirdList;
                }
            }
        }
        List<UserRebateDetailsResponse> detailsList = OrikaMapper.mapList(returnList, UserRebateDetailsResponse.class);
        for(UserRebateDetailsResponse userRebateDetailsResponse: detailsList){
            UserRebate pUserRebate = selectById(userRebateDetailsResponse.getParentId());
            userRebateDetailsResponse.setParent(OrikaMapper.map(pUserRebate, UserRebateDetailsResponse.class));
        }
        UserRebateListResponse response = new UserRebateListResponse();
        response.setList(detailsList);
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserRebatePagerResponse selectPager(UserRebatePagerRequest request){
        UserRebate userRebate = OrikaMapper.map(request, UserRebate.class);
        Pager<UserRebate> pager = OrikaMapper.map(request, Pager.class);
        pager.setObject(userRebate);
        pager = userRebateDao.selectPager(pager);
        List<UserRebateDetailsResponse> detailsList = OrikaMapper.mapList(pager.getData(), UserRebateDetailsResponse.class);
        UserRebatePagerResponse response = OrikaMapper.map(pager, UserRebatePagerResponse.class);
        for(UserRebateDetailsResponse userRebateDetailsResponse: detailsList){
            UserRebate pUserRebate = selectById(userRebateDetailsResponse.getParentId());
            userRebateDetailsResponse.setParent(OrikaMapper.map(pUserRebate, UserRebateDetailsResponse.class));
        }
        response.setList(detailsList);
        return response;
    }

//    @Override
//    public UserRebate selectByUserId(String userId){
//        JSONObject param = new JSONObject();
//        param.put("userId", userId);
//        List<UserRebate> list =  selectAll(param);
//        if(list.size() > 0){
//            return list.get(0);
//        }
//        return null;
//    }

    @Override
    public UserRebate selectByUserNum(String userNum){
        JSONObject param = new JSONObject();
        param.put("userNum", userNum);
        List<UserRebate> list =  selectAll(param);
        if(list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<UserRebate> selectByParentId(String parentId){
        JSONObject param = new JSONObject();
        param.put("parentId", parentId);
        List<UserRebate> list =  selectAll(param);
        return list;
    }

    @Override
    public synchronized UserRebate register(String openid, String unionid, Integer appId, String codeId){
        net.sf.json.JSONObject param = new net.sf.json.JSONObject();
        param.put("openId", openid);
        List<UserOpen> userOpenList = userOpenService.selectAll(param);
        UserRebate userRebate = new UserRebate();
        if(userOpenList.size() == 0){
            userRebate = creatUserRebate(unionid, codeId);
            creatUserOpen(openid, unionid, appId, userRebate.getId());
            creatCode(userRebate.getId());
        }
        return userRebate;
    }


    public UserRebate creatUserRebate(String unionid, String codeId){
        //生成会员信息
        UserRebate userRebate = new UserRebate();
        userRebate.setUnionid(unionid);
        if("".equals(codeId)){
            userRebate.setLevel(1);
            userRebate.setParentId("0");
            userRebate.setTopAgentId("0");
            userRebate.setTopId("0");
        }else{
            Code code1 = codeService.selectById(codeId);
            UserRebate olduserRebate = selectById(code1.getUserId());
            userRebate.setLevel(olduserRebate.getLevel() + 1);
            userRebate.setParentId(olduserRebate.getId());
            if(olduserRebate.getGrade() >= 3){
                userRebate.setTopAgentId("0");
            }else{
                userRebate.setTopAgentId(olduserRebate.getTopAgentId());
            }
            if("0".equals(olduserRebate.getTopId())){
                userRebate.setTopId(olduserRebate.getId());
            }else{
                userRebate.setTopId(olduserRebate.getTopId());
            }
        }
        userRebate.setGrade(0);
        userRebate.setBalance(new BigDecimal(0.0));
        net.sf.json.JSONObject paramCode = new net.sf.json.JSONObject();
        while(true){
            String userNum = IdUtil.generateShortUuid();
            paramCode.put("userNum", userNum);
            List<UserRebate> oldUserRebateList = selectAll(paramCode);
            if(oldUserRebateList.size() == 0){
                userRebate.setUserNum(userNum);
                break;
            }
        }

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(date);
        userRebate.setCreateDate(dateNowStr);
        insert(userRebate);
        return userRebate;
    }

    public UserOpen creatUserOpen(String openid, String unionid, Integer appId, String userId){
        //创建个人应用登录信息
        UserOpen userOpen = new UserOpen();
        userOpen.setAppId(appId);
        userOpen.setUserId(userId);
        userOpen.setOpenId(openid);
        userOpen.setUnionId(unionid);
        userOpenService.insert(userOpen);
        return userOpen;
    }

    public Code creatCode(String userId){
        //创建个人应用推广信息
        Code code = new Code();
        code.setId(IdGen.uuid());
        code.setAppType(1);
        code.setGoodsId("index");
        code.setUserId(userId);
        codeService.insert(code);
        return code;
    }

    /**
     * 创建用户
     * @param userJson
     * @return
     */
    @Transactional
    @Override
    public synchronized UserRebate creat(com.alibaba.fastjson.JSONObject userJson, String codeId, Integer appId){
        //创建应用信息
        String openid = userJson.getString("openId");
        String unionid = userJson.getString("unionId");
        net.sf.json.JSONObject param = new net.sf.json.JSONObject();
        param.put("openId", openid);
        List<UserOpen> userOpenList = userOpenService.selectAll(param);
//        List<UserRebate> userList = selectAll(param);
        UserRebate userRebate = new UserRebate();
        if(userOpenList.size() == 0){
            register(openid, unionid, appId, codeId);
            userRebate = creat(userJson, codeId, appId);
            return userRebate;
        }else{
            UserOpen userOpen = userOpenList.get(0);
            userOpen.setUnionId(unionid);
            userRebate = selectById(userOpen.getUserId());
            if(userRebate.getUsername() == null || "".equals(userRebate.getUsername())){
                userRebate = updateUserRebate(userRebate, userJson);
                return userRebate;
            }
        }
        return userRebate;
    }

    public UserRebate updateUserRebate(UserRebate userRebate, com.alibaba.fastjson.JSONObject userJson){
        String unionid = userJson.getString("unionId");
        userRebate.setUnionid(unionid);
        String nickName = userJson.getString("nickName");
        if(EmojiFilter.containsEmoji(nickName)){
            nickName = EmojiFilter.filterEmoji(nickName);
        }
        userRebate.setUsername(nickName);
        userRebate.setHeadImage(userJson.getString("avatarUrl"));
        update(userRebate);
        return userRebate;
    }

}
