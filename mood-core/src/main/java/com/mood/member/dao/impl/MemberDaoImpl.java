//package com.mood.member.dao.impl;
//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.mood.base.Pager;
//import com.mood.entity.Member;
//import com.mood.member.dao.MemberDao;
//import com.mood.member.dao.mapper.MemberMapper;
//import net.sf.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import tk.mybatis.mapper.entity.Example;
//
//import java.util.List;
//
///**
// * 用户模块
// * @author chaiwei
// * @time 2018-01-07 下午08:00
// */
//@Repository
//public class MemberDaoImpl implements MemberDao {
//
//   @Autowired
//    private MemberMapper memberMapper;
//
//    @Override
//    public int insert(Member member) {
//        return memberMapper.insert(member);
//    }
//
//    @Override
//    public int update(Member member) {
//        return memberMapper.updateByPrimaryKeySelective(member);
//    }
//
//    @Override
//    public int deleteById(String id) {
//        return memberMapper.deleteByPrimaryKey(id);
//    }
//
//    @Override
//    public Member selectById(String id) {
//        return memberMapper.selectByPrimaryKey(id);
//    }
//
//    @Override
//    public List<Member> selectAll(JSONObject param) {
//        Example example = new Example(Member.class);
//        Example.Criteria criteria = example.createCriteria();
//        if (param != null) {
//            //拼接条件
//            Object username = param.get("name");
//            if (username != null) {
//                criteria.andEqualTo("memberName", "%" + username.toString() + "%");
//            }
//            Object openid = param.get("openid");
//            if (openid != null) {
//                criteria.andEqualTo("memberOpenid", openid.toString());
//            }
//            Object unionid = param.get("unionid");
//            if (unionid != null) {
//                criteria.andEqualTo("memberUnionid", unionid.toString());
//            }
//            Object mobile = param.get("mobile");
//            if (mobile != null) {
//                criteria.andEqualTo("memberMobile", mobile.toString());
//            }
//        }
//        example.setOrderByClause("member_id asc");
//        return memberMapper.selectByExample(example);
//    }
//
//    @Override
//    public Pager<Member> selectPager(Pager pager){
//        Example example = new Example(Member.class);
//        Example.Criteria criteria = example.createCriteria();
//        //查询条件
//        JSONObject param = pager.getParams();
//        if (param != null) {
//            //拼接条件
//        }
//        //处理分页
//        PageHelper.startPage(pager.getPageIndex(), pager.getPageSize());
//        List<Member> result = memberMapper.selectByExample(example);
//        //组装返回数据
//        PageInfo pageInfo = new PageInfo(result);
//        return pager.buildPager(pageInfo.getPages(), pageInfo.getTotal(), pageInfo.getList());
//    }
//
//}
