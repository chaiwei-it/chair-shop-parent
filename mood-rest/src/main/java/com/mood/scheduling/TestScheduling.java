package com.mood.scheduling;

import com.mood.entity.goods.request.IndexGoodsListRequest;
import com.mood.entity.goods.response.IndexGoodsListResponse;
import com.mood.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 描述:
 *
 * @author chaiwei
 * @create 2018-10-15 下午3:55
 */
@Component
public class TestScheduling {

    @Value("${test}")
    private String test;

//    @Scheduled(fixedRate = 5000)
//    public void scheduled1() {
//        System.out.println("定时执行" + test);
//        System.out.println(goodsService.getClass());
//        IndexGoodsListRequest request = new IndexGoodsListRequest();
//        request.setKeywords("hottest");
//        IndexGoodsListResponse indexGoodsListResponse = goodsService.selectIndexList(request);
//        System.out.println("热销商品" + indexGoodsListResponse);
//
//    }
}
