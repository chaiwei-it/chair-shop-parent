package com.mood.extensionGoods.service.impl;

import com.mood.base.Pager;
import com.mood.entity.extensionGoods.Extension;
import com.mood.entity.extensionGoods.request.*;
import com.mood.entity.extensionGoods.response.*;
import com.mood.entity.goods.Goods;
import com.mood.extensionGoods.dao.ExtensionGoodsDao;
import com.mood.extensionGoods.service.ExtensionGoodsService;
import com.mood.goods.dao.GoodsDao;
import com.mood.utils.IdGen;
import com.mood.utils.OrikaMapper;
import net.sf.json.JSONObject;
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
public class ExtensionGoodsServiceImpl implements ExtensionGoodsService {

    @Autowired
    private ExtensionGoodsDao extensionDao;

    @Autowired
    private GoodsDao goodsDao;


    @Override
    public int insert(Extension extensionGoods) {
        extensionGoods.setId(IdGen.uuid());
        extensionGoods.setCreateTime(System.currentTimeMillis());
        extensionGoods.setUpdateTime(System.currentTimeMillis());
        extensionGoods.setDelStatus(0);
        return extensionDao.insert(extensionGoods);
    }

    @Override
    public int update(Extension extensionGoods) {
        extensionGoods.setUpdateTime(System.currentTimeMillis());
        return extensionDao.update(extensionGoods);
    }

    @Override
    public int deleteById(String id) {
        return extensionDao.deleteById(id);
    }

    @Override
    public Extension selectById(String id) {
        return extensionDao.selectById(id);
    }

    @Override
    public List<Extension> selectAll(JSONObject param) {
        return extensionDao.selectAll(param);
    }

    @Override
    public Pager<Extension> selectPager(Pager pager){
        return extensionDao.selectPager(pager);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ExtensionInsertResponse insert(ExtensionInsertRequest request){
        Extension banner = OrikaMapper.map(request, Extension.class);
        banner.setId(IdGen.uuid());
        extensionDao.insert(banner);
        return OrikaMapper.map(banner, ExtensionInsertResponse.class);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ExtensionUpdateResponse update(ExtensionUpdateRequest request){
        Extension banner = OrikaMapper.map(request, Extension.class);
        extensionDao.update(banner);
        return OrikaMapper.map(banner, ExtensionUpdateResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ExtensionDeleteResponse delete(ExtensionDeleteRequest request){
        extensionDao.deleteById(request.getId());
        return new ExtensionDeleteResponse();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ExtensionSelectResponse select(ExtensionSelectRequest request){
        Extension banner = extensionDao.selectById(request.getId());
        return OrikaMapper.map(banner, ExtensionSelectResponse.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ExtensionListResponse selectList(ExtensionListRequest request){
        Extension banner = OrikaMapper.map(request, Extension.class);
        List<Extension> bannerList = extensionDao.selectAll(banner);
        List<ExtensionDetailsResponse> detailsList = OrikaMapper.mapList(bannerList, ExtensionDetailsResponse.class);
        ExtensionListResponse response = new ExtensionListResponse();
        response.setList(detailsList);
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ExtensionPagerResponse selectPager(ExtensionPagerRequest request){
        Extension banner = OrikaMapper.map(request, Extension.class);
        Pager<Extension> pager = OrikaMapper.map(request, Pager.class);
        pager.setObject(banner);
        pager = extensionDao.selectPager(pager);
        List<ExtensionDetailsResponse> detailsList = new ArrayList<ExtensionDetailsResponse>();
        for(Extension extension: pager.getData()){
            ExtensionDetailsResponse extensionDetailsResponse = OrikaMapper.map(extension, ExtensionDetailsResponse.class);
            Goods goods = goodsDao.selectById(extensionDetailsResponse.getGoodsId());
//            if(goods != null){
                extensionDetailsResponse.setGoods(goods);
                detailsList.add(extensionDetailsResponse);
//            }
        }
        ExtensionPagerResponse response = OrikaMapper.map(pager, ExtensionPagerResponse.class);
        response.setList(detailsList);
        return response;
    }

    /**
     * 通过关键字获取关联商品
     * @param keywords
     * @return
     */
    @Override
    public List<Extension> selectByKeywords(String keywords){
        JSONObject param = new JSONObject();
        param.put("keywords", keywords);
        List<Extension> extensionGoodsList = selectAll(param);
        return extensionGoodsList;
    }
}
