package com.mood.controller.goods;

import com.mood.base.BaseController;
import com.mood.entity.goods.notes.GoodsListNote;
import com.mood.entity.goods.request.*;
import com.mood.entity.goods.response.*;
import com.mood.goods.service.GoodsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * 模块
 * @author chaiwei
 * @time 2018-05-15 下午10:00
 */
@RestController
@RequestMapping("/api/{version}/goods")
public class GoodsApi extends BaseController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 推荐商品
     * @param request
     * @return
     */
    @ApiOperation(value = GoodsListNote.title, notes = GoodsListNote.notes)
    @PostMapping("indexList")
    public IndexGoodsListResponse list(@Valid @RequestBody IndexGoodsListRequest request, BindingResult error){
        return goodsService.selectIndexList(request);
    }

    /**
     * 分类商品
     * @param request
     * @return
     */
    @ApiOperation(value = GoodsListNote.title, notes = GoodsListNote.notes)
    @PostMapping("classList")
    public ClassGoodsListResponse classList(@Valid @RequestBody ClassGoodsListRequest request, BindingResult error){
        return goodsService.selectClassGoodsList(request);
    }

    /**
     * 自营商品
     * @param request
     * @return
     */
    @ApiOperation(value = GoodsListNote.title, notes = GoodsListNote.notes)
    @PostMapping("selfGoodList")
    public SelfGoodsListResponse selfGoodList(@Valid @RequestBody SelfGoodsListRequest request, BindingResult error){
        return goodsService.selectSelfGoodsList(request);
    }

    /**
     * 商品详情
     * @param request
     * @return
     */
    @ApiOperation(value = GoodsListNote.title, notes = GoodsListNote.notes)
    @PostMapping("select")
    public GoodsSelectResponse select(@Valid @RequestBody GoodsSelectRequest request, BindingResult error){
        return goodsService.select(request);
    }

}
