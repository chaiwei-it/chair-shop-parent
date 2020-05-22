package com.mood.code.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mood.base.Pager;
import com.mood.code.dao.CodeDao;
import com.mood.code.dao.mapper.CodeMapper;
import com.mood.entity.rabate.Code;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 用户模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
@Repository
public class CodeDaoImpl implements CodeDao {

   @Autowired
    private CodeMapper codeMapper;

    @Override
    public int insert(Code code) {
        return codeMapper.insert(code);
    }

    @Override
    public int update(Code code) {
        return codeMapper.updateByPrimaryKeySelective(code);
    }

    @Override
    public int deleteById(String id) {
        return codeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Code selectById(String id) {
        return codeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Code> selectAll(JSONObject param) {
        Example example = new Example(Code.class);
        Example.Criteria criteria = example.createCriteria();
        if (param != null) {
            //拼接条件
            Object userId = param.get("userId");
            if (userId != null) {
                criteria.andEqualTo("userId", userId.toString());
            }
            Object goodsId = param.get("goodsId");
            if (goodsId != null) {
                criteria.andEqualTo("goodsId", goodsId.toString());
            }
            Object appType = param.get("appType");
            if (appType != null) {
                criteria.andEqualTo("appType", appType.toString());
            }
        }
        example.setOrderByClause("id asc");
        return codeMapper.selectByExample(example);
    }

    @Override
    public Pager<Code> selectPager(Pager pager){
        Example example = new Example(Code.class);
        Example.Criteria criteria = example.createCriteria();
        //查询条件
        JSONObject param = pager.getParams();
        if (param != null) {
            //拼接条件
        }
        //处理分页
        PageHelper.startPage(pager.getPageIndex(), pager.getPageSize());
        List<Code> result = codeMapper.selectByExample(example);
        //组装返回数据
        PageInfo pageInfo = new PageInfo(result);
        return pager.buildPager(pageInfo.getPages(), pageInfo.getTotal(), pageInfo.getList());
    }

}
