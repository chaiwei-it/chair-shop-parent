package com.mood.code.service.impl;

import com.mood.base.Pager;
import com.mood.entity.rabate.Code;
import com.mood.code.dao.CodeDao;
import com.mood.code.service.CodeService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private CodeDao codeDao;


    @Override
    public int insert(Code code) {
//        code.setId(IdGen.uuid());
        code.setCreateTime(System.currentTimeMillis());
        code.setUpdateTime(System.currentTimeMillis());
        code.setDelStatus(0);
        return codeDao.insert(code);
    }

    @Override
    public int update(Code code) {
        code.setUpdateTime(System.currentTimeMillis());
        return codeDao.update(code);
    }

    @Override
    public int deleteById(String id) {
        return codeDao.deleteById(id);
    }

    @Override
    public Code selectById(String id) {
        return codeDao.selectById(id);
    }

    @Override
    public List<Code> selectAll(JSONObject param) {
        return codeDao.selectAll(param);
    }

    @Override
    public Pager<Code> selectPager(Pager pager){
        return codeDao.selectPager(pager);
    }

    @Override
    public Code selectByUserId(String userId){
        JSONObject param = new JSONObject();
        param.put("userId", userId);
        List<Code> list =  selectAll(param);
        if(list.size() > 0){
            return list.get(0);
        }
        return new Code();
    }

}
