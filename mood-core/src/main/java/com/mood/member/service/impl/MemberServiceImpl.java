//package com.mood.member.service.impl;
//
//import com.alibaba.fastjson.JSONObject;
//import com.mood.base.Pager;
//import com.mood.code.service.CodeService;
//import com.mood.entity.Member;
//import com.mood.entity.rabate.Code;
//import com.mood.entity.rabate.UserOpen;
//import com.mood.entity.userRebate.UserRebate;
//import com.mood.member.dao.MemberDao;
//import com.mood.member.service.MemberService;
//import com.mood.userOpen.service.UserOpenService;
//import com.mood.userRebate.service.UserRebateService;
//import com.mood.utils.EmojiFilter;
//import com.mood.utils.IdGen;
//import com.mood.utils.IdUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.math.BigDecimal;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
///**
// * 用户模块
// * @author chaiwei
// * @time 2018-01-07 下午08:00
// */
//@Service
//public class MemberServiceImpl implements MemberService {
//
//    @Autowired
//    private MemberDao memberDao;
//
//    @Autowired
//    private UserRebateService userRebateService;
//
//    @Autowired
//    private UserOpenService userOpenService;
//
//    @Autowired
//    private CodeService codeService;
//
//
//    @Override
//    public int insert(Member member) {
//        member.setMemberId(IdGen.uuid());
//        return memberDao.insert(member);
//    }
//
//    @Override
//    public int update(Member member) {
//        return memberDao.update(member);
//    }
//
//    @Override
//    public int deleteById(String id) {
//        return memberDao.deleteById(id);
//    }
//
//    @Override
//    public Member selectById(String id) {
//        return memberDao.selectById(id);
//    }
//
//    @Override
//    public List<Member> selectAll(net.sf.json.JSONObject param) {
//        return memberDao.selectAll(param);
//    }
//
//    @Override
//    public Pager<Member> selectPager(Pager pager){
//        return memberDao.selectPager(pager);
//    }
//
//    /**
//     * 通过手机号获取用户
//     * @param mobile
//     * @return
//     */
//    @Override
//    public Member selectByMobile(String mobile){
//        net.sf.json.JSONObject param = new net.sf.json.JSONObject();
//        if(!"".equals(mobile)){
//            param.put("mobile",mobile);
//        }
//        List<Member> userList = selectAll(param);
//        if(userList.size() > 0){
//            return userList.get(0);
//        }else {
//            return null;
//        }
//    }
//
//    @Override
//    public synchronized Member register(String openid, String unionid, Integer appId, String codeId){
//        net.sf.json.JSONObject param = new net.sf.json.JSONObject();
//        param.put("unionid", unionid);
//        List<Member> userList = selectAll(param);
//        Member member = new Member();
//        if(userList.size() == 0){
//            member = creatMember(unionid);
//            creatUserOpen(openid, unionid, appId, member.getMemberId());
//        }
//        return member;
//    }
//
//    /**
//     * 创建用户
//     * @param userJson
//     * @return
//     */
//    @Transactional
//    @Override
//    public synchronized Member creat(JSONObject userJson, String codeId, Integer appId){
//        //创建应用信息
//        String openid = userJson.getString("openId");
//        String unionid = userJson.getString("unionId");
//        net.sf.json.JSONObject param = new net.sf.json.JSONObject();
//        param.put("unionid", unionid);
//        List<Member> userList = selectAll(param);
//        Member member = new Member();
//        if(userList.size() == 0){
//            member = register(openid, unionid, appId, codeId);
//            return member;
//        }else{
//            member = userList.get(0);
//            if(member.getMemberName() == null || "".equals(member.getMemberName())){
//                member = updateMember(member, userJson);
//
//            }
//        }
//        return member;
//    }
//
//    Member creatMember(String unionid){
//        Member member = new Member();
//        member.setMemberUnionid(unionid);
//        insert(member);
//        return member;
//    }
//
//    Member updateMember(Member member, JSONObject userJson){
//        String nickName = userJson.getString("nickName");
//        if(EmojiFilter.containsEmoji(nickName)){
//            nickName = EmojiFilter.filterEmoji(nickName);
//        }
//        member.setMemberName(nickName);
//        member.setMemberTruename(nickName);
//        member.setMemberAvatar(userJson.getString("avatarUrl"));
//        update(member);
//        return member;
//    }
//
//    UserOpen creatUserOpen(String openid, String unionid, Integer appId, String userId){
//        //创建个人应用登录信息
//        UserOpen userOpen = new UserOpen();
//        userOpen.setAppId(appId);
//        userOpen.setUserId(userId);
//        userOpen.setOpenId(openid);
//        userOpen.setUnionId(unionid);
//        userOpenService.insert(userOpen);
//        return userOpen;
//    }
//
//    UserRebate creatUserRebate(String userId, JSONObject userJson, String codeId){
//        //生成会员信息
//        UserRebate userRebate = new UserRebate();
//        userRebate.setUserId(userId);
//        String nickName = userJson.getString("nickName");
//        if(EmojiFilter.containsEmoji(nickName)){
//            nickName = EmojiFilter.filterEmoji(nickName);
//        }
//        userRebate.setUsername(nickName);
//        userRebate.setHeadImage(userJson.getString("avatarUrl"));
//        if("".equals(codeId)){
//            userRebate.setLevel(1);
//            userRebate.setParentId("0");
//            userRebate.setTopAgentId("0");
//            userRebate.setTopId("0");
//        }else{
//            Code code1 = codeService.selectById(codeId);
//            UserRebate olduserRebate = userRebateService.selectByUserId(code1.getUserId());
//            userRebate.setLevel(olduserRebate.getLevel() + 1);
//            userRebate.setParentId(olduserRebate.getUserId());
//            if(olduserRebate.getGrade() >= 3){
//                userRebate.setTopAgentId("0");
//            }else{
//                userRebate.setTopAgentId("0");
//            }
//            if("0".equals(olduserRebate.getTopId())){
//                userRebate.setTopId(olduserRebate.getUserId());
//            }else{
//                userRebate.setTopId(olduserRebate.getTopId());
//            }
//        }
//        userRebate.setGrade(0);
//        userRebate.setBalance(new BigDecimal(0.0));
//        String userNum = IdUtil.generateShortUuid();
//        net.sf.json.JSONObject paramCode = new net.sf.json.JSONObject();
//        while(true){
//            paramCode.put("userNum", userNum);
//            List<UserRebate> oldUserRebateList = userRebateService.selectAll(paramCode);
//            if(oldUserRebateList.size() == 0){
//                break;
//            }
//            userNum = IdUtil.generateShortUuid();
//        }
//        userRebate.setUserNum(userNum);
//        Date d = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dateNowStr = sdf.format(d);
//        userRebate.setCreateDate(dateNowStr);
//        userRebateService.insert(userRebate);
//        return userRebate;
//    }
//
//    Code creatCode(String userId){
//        //创建个人应用推广信息
//        Code code = new Code();
//        code.setId(IdGen.uuid());
//        code.setAppType(1);
//        code.setGoodsId("index");
//        code.setUserId(userId);
//        codeService.insert(code);
//        return code;
//    }
//
//}
