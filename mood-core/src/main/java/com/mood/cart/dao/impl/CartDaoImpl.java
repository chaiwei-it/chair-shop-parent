package com.mood.cart.dao.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mood.base.Pager;
import com.mood.entity.cart.Cart;
import com.mood.cart.dao.CartDao;
import com.mood.cart.dao.mapper.CartMapper;
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
public class CartDaoImpl implements CartDao {

   @Autowired
    private CartMapper cartMapper;

    @Override
    public int insert(Cart cart) {
        return cartMapper.insert(cart);
    }

    @Override
    public int update(Cart cart) {
        return cartMapper.updateByPrimaryKeySelective(cart);
    }

    @Override
    public int deleteById(String id) {
        return cartMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Cart selectById(String id) {
        return cartMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Cart> selectAll(JSONObject param){
        return null;
    }

    @Override
    public List<Cart> selectAll(Cart cart, String... data) {
        Example example = new Example(Cart.class);
        Example.Criteria criteria = example.createCriteria();
        if (cart != null) {
            //拼接条件
            String goodsId = cart.getGoodsId();
            if (goodsId != null && !"".equals(goodsId)) {
                criteria.andEqualTo("goodsId", goodsId);
            }
            String specId = cart.getSpecId();
            if (specId != null && !"".equals(specId)) {
                criteria.andEqualTo("specId", specId);
            }
            String userId = cart.getUserId();
            if (userId != null && !"".equals(userId)) {
                criteria.andEqualTo("userId", userId);
            }
        }
        String orderBy = "";
        if(data.length > 0){
            orderBy = data[0];
        }else{
            orderBy = "id asc";
        }
        if(!"".equals(orderBy)){
            example.setOrderByClause(orderBy);
        }
        return cartMapper.selectByExample(example);
    }

    @Override
    public Pager<Cart> selectPager(Pager pager){
        //处理分页
        PageHelper.startPage(pager.getPageIndex(), pager.getPageSize());
        List<Cart> result = selectAll((Cart)pager.getObject(), pager.getOrderBy());
        //组装返回数据
        PageInfo pageInfo = new PageInfo(result);
        return pager.buildPager(pageInfo.getPages(), pageInfo.getTotal(), pageInfo.getList());
    }

}
