package com.mood.typeSpec.dao;

import com.mood.base.dao.BaseDao;
import com.mood.entity.typeSpec.TypeSpec;

import java.util.List;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
public interface TypeSpecDao extends BaseDao<TypeSpec> {

    public List<TypeSpec> selectAll(TypeSpec t, String... data);

}
