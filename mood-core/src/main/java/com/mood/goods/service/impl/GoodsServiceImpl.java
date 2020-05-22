package com.mood.goods.service.impl;

import com.mood.base.Pager;
import com.mood.entity.extensionGoods.Extension;
import com.mood.entity.goods.Goods;
import com.mood.entity.goods.request.*;
import com.mood.entity.goods.response.*;
import com.mood.entity.goodsClass.GoodsClass;
import com.mood.entity.goodsRebate.GoodsRebate;
import com.mood.entity.goodsSpec.GoodsSpec;
import com.mood.entity.goodsType.GoodsType;
import com.mood.extensionGoods.service.ExtensionGoodsService;
import com.mood.goods.dao.GoodsDao;
import com.mood.goods.service.GoodsService;
import com.mood.goodsClass.service.GoodsClassService;
import com.mood.goodsRebate.service.GoodsRebateService;
import com.mood.goodsSpec.service.GoodsSpecService;
import com.mood.goodsType.service.GoodsTypeService;
import com.mood.utils.IdGen;
import com.mood.utils.OrikaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户模块
 * @author chaiwei
 * @time 2018-01-07 下午08:00
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private GoodsRebateService goodsRebateService;

    @Autowired
    private GoodsSpecService goodsSpecService;

    @Autowired
    private GoodsTypeService goodsTypeService;

    @Autowired
    private GoodsClassService goodsClassService;

    @Autowired
    private ExtensionGoodsService extensionGoodsService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public GoodsInsertResponse insert(GoodsInsertRequest request){
        //商品信息
        Goods goods = OrikaMapper.map(request, Goods.class);
        goods.setId(IdGen.uuid());
        GoodsType goodsType = goodsTypeService.selectById(request.getTypeId());
        if(goodsType != null){
            goods.setTypeName(goodsType.getName());
            GoodsClass goodsClass = goodsClassService.selectById(goodsType.getClassId());
            if(goodsClass != null){
                goods.setClassId(goodsClass.getId());
                goods.setClassName(goodsClass.getName());
                goods.setKeywords(goodsClass.getKeywords());
            }
        }
        goods.setSales(0);
        goods.setGoodsType(1);
        goods.setStatus(1);
        goods.setCreateTime(System.currentTimeMillis());
        goods.setUpdateTime(System.currentTimeMillis());
        goods.setDelStatus(0);
        //返利信息
        GoodsRebate goodsRebate = OrikaMapper.map(request, GoodsRebate.class);
        goodsRebate.setGoodsId(goods.getId());
        goodsRebate.setStoreType(0);
        goodsRebateService.insert(goodsRebate);
        //规格信息
        List<AddGoodsSpecRequest> goodsSpecList = request.getGoodsSpecList();
        for (AddGoodsSpecRequest addGoodsSpecRequest: goodsSpecList) {
            GoodsSpec goodsSpec = OrikaMapper.map(addGoodsSpecRequest, GoodsSpec.class);
            goodsSpec.setGoodsId(goods.getId());
            goodsSpecService.insert(goodsSpec);
        }
        goodsDao.insert(goods);
        return OrikaMapper.map(goods, GoodsInsertResponse.class);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public GoodsUpdateResponse update(GoodsUpdateRequest request){
        //商品信息
        Goods goods = OrikaMapper.map(request, Goods.class);
        GoodsType goodsType = goodsTypeService.selectById(request.getTypeId());
        if(goodsType != null){
            goods.setTypeName(goodsType.getName());
            GoodsClass goodsClass = goodsClassService.selectById(goodsType.getClassId());
            if(goodsClass != null){
                goods.setClassId(goodsClass.getId());
                goods.setClassName(goodsClass.getName());
                goods.setKeywords(goodsClass.getKeywords());
            }
        }
        goods.setUpdateTime(System.currentTimeMillis());
        //返利信息
        goodsRebateService.deleteByGoodsId(goods.getId());
        GoodsRebate goodsRebate = OrikaMapper.map(request, GoodsRebate.class);
        goodsRebate.setGoodsId(goods.getId());
        goodsRebate.setStoreType(0);
        goodsRebateService.insert(goodsRebate);
        //规格信息
        goodsSpecService.deleteByGoodsId(goods.getId());
        List<AddGoodsSpecRequest> goodsSpecList = request.getGoodsSpecList();
        for (AddGoodsSpecRequest addGoodsSpecRequest: goodsSpecList) {
            GoodsSpec goodsSpec = OrikaMapper.map(addGoodsSpecRequest, GoodsSpec.class);
            goodsSpec.setGoodsId(goods.getId());
            goodsSpecService.insert(goodsSpec);
        }
        goodsDao.update(goods);
        return OrikaMapper.map(goods, GoodsUpdateResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public GoodsDeleteResponse delete(GoodsDeleteRequest request){
        goodsDao.deleteById(request.getId());
        return new GoodsDeleteResponse();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public GoodsSelectResponse select(GoodsSelectRequest request){
        Goods goods = goodsDao.selectById(request.getId());
        GoodsSelectResponse goodsSelectResponse = OrikaMapper.map(goods, GoodsSelectResponse.class);
        //构建返利信息
        GoodsRebate goodsRebate = goodsRebateService.selectByGoodId(request.getId());
        goodsSelectResponse.setMemberPrice(goodsRebate.getMemberPrice());
        goodsSelectResponse.setVipPrice(goodsRebate.getVipPrice());
        goodsSelectResponse.setAgentPrice(goodsRebate.getAgentPrice());
        goodsSelectResponse.setAreaPrice(goodsRebate.getAreaPrice());
        goodsSelectResponse.setCityPrice(goodsRebate.getCityPrice());
        goodsSelectResponse.setProvincePrice(goodsRebate.getProvincePrice());
        //构建规格信息
        List<GoodsSpec> goodsSpecList = goodsSpecService.selectByGoodsId(request.getId());
        List<AddGoodsSpecRequest> detailsList = OrikaMapper.mapList(goodsSpecList, AddGoodsSpecRequest.class);
        goodsSelectResponse.setGoodsSpecList(detailsList);
        return goodsSelectResponse;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public GoodsListResponse selectList(GoodsListRequest request){
        Goods goods = OrikaMapper.map(request, Goods.class);
        List<Goods> goodsList = goodsDao.selectAll(goods);
        List<GoodsDetailsResponse> detailsList = OrikaMapper.mapList(goodsList, GoodsDetailsResponse.class);
        GoodsListResponse response = new GoodsListResponse();
        response.setList(detailsList);
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public GoodsPagerResponse selectPager(GoodsPagerRequest request){
        Goods goods = OrikaMapper.map(request, Goods.class);
        Pager<Goods> pager = OrikaMapper.map(request, Pager.class);
        pager.setObject(goods);
        pager = goodsDao.selectPager(pager);
        List<GoodsDetailsResponse> detailsList = OrikaMapper.mapList(pager.getData(), GoodsDetailsResponse.class);
        GoodsPagerResponse response = OrikaMapper.map(pager, GoodsPagerResponse.class);
        response.setList(detailsList);
        return response;
    }

    /**
     * 获取首页推广商品
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public IndexGoodsListResponse selectIndexList(IndexGoodsListRequest request){
        List<Extension> extensionGoodsList = extensionGoodsService.selectByKeywords(request.getKeywords());
        IndexGoodsListResponse indexGoodsListResponse = new IndexGoodsListResponse();
        List<IndexGoodsDetailsResponse> list = new ArrayList<IndexGoodsDetailsResponse>();
        for(Extension extensionGoods: extensionGoodsList){
            Goods goods = goodsDao.selectById(extensionGoods.getGoodsId());
            IndexGoodsDetailsResponse indexGoodsDetailsResponse = OrikaMapper.map(goods, IndexGoodsDetailsResponse.class);
            list.add(indexGoodsDetailsResponse);
        }
        indexGoodsListResponse.setList(list);
        return indexGoodsListResponse;
    }

    /**
     * 获取分类商品
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ClassGoodsListResponse selectClassGoodsList(ClassGoodsListRequest request){
        Goods goods = OrikaMapper.map(request, Goods.class);
        List<Goods> goodsList = goodsDao.selectAll(goods);
        ClassGoodsListResponse classGoodsListResponse = new ClassGoodsListResponse();
        List<ClassGoodsDetailsResponse> list = OrikaMapper.mapList(goodsList, ClassGoodsDetailsResponse.class);
        classGoodsListResponse.setList(list);
        return classGoodsListResponse;
    }

    /**
     * 获取自营商品
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SelfGoodsListResponse selectSelfGoodsList(SelfGoodsListRequest request){
        Goods goods = OrikaMapper.map(request, Goods.class);
        List<Goods> goodsList = goodsDao.selectAll(goods);
        SelfGoodsListResponse selfGoodsListResponse = new SelfGoodsListResponse();
        List<SelfGoodsDetailsResponse> list = OrikaMapper.mapList(goodsList, SelfGoodsDetailsResponse.class);
        selfGoodsListResponse.setList(list);
        return selfGoodsListResponse;
    }

    /**
     * 修改商品状态
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public GoodsUpdateStatusResponse updateStatus(GoodsUpdateStatusRequest request){
        Goods goods = OrikaMapper.map(request, Goods.class);
        goodsDao.update(goods);
        return new GoodsUpdateStatusResponse();
    }

}
