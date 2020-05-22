package com.mood.cart.service.impl;

import com.mood.Shift;
import com.mood.StatusCode;
import com.mood.base.Pager;
import com.mood.entity.cart.Cart;
import com.mood.entity.cart.request.*;
import com.mood.entity.cart.response.*;
import com.mood.cart.dao.CartDao;
import com.mood.cart.service.CartService;
import com.mood.entity.goods.Goods;
import com.mood.entity.goodsSpec.GoodsSpec;
import com.mood.goods.dao.GoodsDao;
import com.mood.goodsSpec.dao.GoodsSpecDao;
import com.mood.utils.IdGen;
import com.mood.utils.OrikaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private GoodsSpecDao goodsSpecDao;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public CartInsertResponse insert(CartInsertRequest request){
        //查询是否已经存在此规格商品
        Cart cart = new Cart();
        cart.setUserId(request.getUserId());
        cart.setGoodsId(request.getGoodsId());
        cart.setSpecId(request.getSpecId());
        List<Cart> cartList = cartDao.selectAll(cart);
        if(cartList.size() > 0){
            cart = cartList.get(0);
            cart.setGoodsNum(cart.getGoodsNum() + request.getGoodsNum());
            cartDao.update(cart);
        }else {
            cart = OrikaMapper.map(request, Cart.class);
            cart.setId(IdGen.uuid());
            Goods goods = goodsDao.selectById(request.getGoodsId());
            cart.setGoodsName(goods.getName());
            cart.setGoodsImage(goods.getThumbnailImage());
            cart.setStoreId(goods.getStoreId());
            cart.setStoreName(goods.getStoreName());
            if(request.getSpecId() == null || "".equals(request.getSpecId())){
                cart.setGoodsPrice(goods.getNowPrice());
            }else{
                GoodsSpec goodsSpec = goodsSpecDao.selectById(request.getSpecId());
                cart.setSpecInfo(goodsSpec.getSpecNames());
                cart.setGoodsPrice(goodsSpec.getNowPrice());
            }
            //查询是否有规格
            cartDao.insert(cart);
        }
        return OrikaMapper.map(cart, CartInsertResponse.class);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CartUpdateResponse update(CartUpdateRequest request){
        Cart cart = OrikaMapper.map(request, Cart.class);
        cart.setUpdateTime(System.currentTimeMillis());
        cartDao.update(cart);
        return OrikaMapper.map(cart, CartUpdateResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CartDeleteResponse delete(CartDeleteRequest request){
        cartDao.deleteById(request.getId());
        return new CartDeleteResponse();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CartSelectResponse select(CartSelectRequest request){
        Cart cart = cartDao.selectById(request.getId());
        return OrikaMapper.map(cart, CartSelectResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CartListResponse selectList(CartListRequest request){
        Cart cart = OrikaMapper.map(request, Cart.class);
        List<Cart> cartList = cartDao.selectAll(cart);
        List<CartDetailsResponse> detailsList = OrikaMapper.mapList(cartList, CartDetailsResponse.class);
        CartListResponse response = new CartListResponse();
        response.setList(detailsList);
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CartPagerResponse selectPager(CartPagerRequest request){
        Cart cart = OrikaMapper.map(request, Cart.class);
        Pager<Cart> pager = OrikaMapper.map(request, Pager.class);
        pager.setObject(cart);
        pager = cartDao.selectPager(pager);
        List<CartDetailsResponse> detailsList = OrikaMapper.mapList(pager.getData(), CartDetailsResponse.class);
        CartPagerResponse response = OrikaMapper.map(pager, CartPagerResponse.class);
        response.setList(detailsList);
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CartNumResponse selectCartNum(CartListRequest request){
        Cart cart = OrikaMapper.map(request, Cart.class);
        List<Cart> cartList = cartDao.selectAll(cart);
        CartNumResponse response = new CartNumResponse();
        Integer goodsNum = 0;
        for(Cart cartGoods: cartList){
            goodsNum += cartGoods.getGoodsNum();
        }
        response.setGoodsNum(goodsNum);
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CartGoodsListResponse selectGoodsList(CartGoodsListRequest request){
        String[] ids = request.getCartIds().split(",");
        List<CartGoodsDetailsResponse> list = new ArrayList<CartGoodsDetailsResponse>();
        BigDecimal goodsPrice = new BigDecimal(0);
        Integer goodsNum = 0;
        for (String id : ids){
            Cart cart = cartDao.selectById(id);
            if(cart != null){
                CartGoodsDetailsResponse cartGoodsDetailsResponse = OrikaMapper.map(cart, CartGoodsDetailsResponse.class);
                list.add(cartGoodsDetailsResponse);
                goodsPrice = goodsPrice.add(cart.getGoodsPrice().multiply(new BigDecimal(cart.getGoodsNum())));
                goodsNum += cart.getGoodsNum();
            }
        }
        CartGoodsListResponse cartGoodsListResponse = new CartGoodsListResponse();
        cartGoodsListResponse.setList(list);
        cartGoodsListResponse.setGoodsPrice(goodsPrice);
        cartGoodsListResponse.setGoodsNum(goodsNum);
        return cartGoodsListResponse;
    }
}
