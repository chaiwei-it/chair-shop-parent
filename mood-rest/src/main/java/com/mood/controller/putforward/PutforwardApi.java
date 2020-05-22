package com.mood.controller.putforward;

import com.mood.base.BaseController;
import com.mood.base.Pager;
import com.mood.common.HttpCode;
import com.mood.entity.putforward.Putforward;
import com.mood.entity.userRebate.UserRebate;
import com.mood.putforward.service.PutforwardService;
import com.mood.userRebate.service.UserRebateService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 模块
 * @author chaiwei
 * @time 2018-05-15 下午10:00
 */
@RestController
@RequestMapping("/api/{version}/putforward")
public class PutforwardApi extends BaseController {

    @Autowired
    private PutforwardService putforwardService;

    @Autowired
    private UserRebateService userRebateService;

    /**
     * 添加
     * @param price
     * @return
     */
    @PostMapping("")
    public ResponseEntity<ModelMap> create(@RequestParam(value = "price", required = false, defaultValue = "") BigDecimal  price,
                                           @RequestParam(value = "mobile", required = false, defaultValue = "") String mobile,
                                           @RequestParam(value = "alipayNum", required = false, defaultValue = "") String alipayNum,
                                           @RequestParam(value = "alipayName", required = false, defaultValue = "") String alipayName,
                                           ModelMap modelMap, HttpServletRequest request){
        String userId = getUserId(request);
        if(userId == null){
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        }
        UserRebate userRebate = userRebateService.selectById(userId);
        if(userRebate.getBalance().compareTo(price) < 0){
            return setModelMap(modelMap, HttpCode.BALABCE_NEVER);
        }
        Putforward putforward = new Putforward();
        putforward.setMobile(mobile);
        putforward.setPrice(price);
        putforward.setStatus(1);
        putforward.setAlipayNum(alipayNum);
        putforward.setAlipayName(alipayName);
        putforward.setUserId(userId);
        putforward.setBalance(userRebate.getBalance().subtract(price));
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(d);
        putforward.setCreateData(dateNowStr);
        Integer result = putforwardService.insert(putforward);
        if(result > 0){
            return setSuccessModelMap(modelMap,putforward);
        }
        return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * 修改
     * @param putforward
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<ModelMap> update(@PathVariable(value = "id", required = false) String id,
                          @ModelAttribute Putforward putforward,
                          ModelMap modelMap, HttpServletRequest request){
        putforward.setId(id);
        Integer result = putforwardService.update(putforward);
        if(result > 0){
            return setSuccessModelMap(modelMap,putforward);
        }
        return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ModelMap> delete(@PathVariable(value = "id", required = false) String id,
                          ModelMap modelMap, HttpServletRequest request){
        Integer result = putforwardService.deleteById(id);
        if(result > 0){
            return setSuccessModelMap(modelMap);
        }
        return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * 详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<ModelMap> select(@PathVariable(value = "id", required = false) String id,
                             ModelMap modelMap, HttpServletRequest request){
        Putforward putforward = putforwardService.selectById(id);
        return setSuccessModelMap(modelMap,putforward);
    }

    /**
     * 列表
     * @param
     * @return
     */
    @GetMapping("getPutList")
    public ResponseEntity<ModelMap> list(ModelMap modelMap, HttpServletRequest request){
        String userId = getUserId(request);
        if(userId == null){
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        }
        JSONObject param = new JSONObject();
        param.put("userId",userId);
        List<Putforward> list = putforwardService.selectAll(param);
        return setSuccessModelMap(modelMap,list);
    }

    /**
     * 分页
     * @param pageIndex
     * @param pageSize
     * @param putforwardName
     * @return
     */
    @GetMapping("pager")
    public ResponseEntity<ModelMap> pager(@RequestParam(value = "pageIndex",required = false,  defaultValue = "1") Integer pageIndex,
                                          @RequestParam(value = "pageSize",required = false,  defaultValue = "20") Integer pageSize,
                                          @RequestParam(value = "putforwardName", required = false, defaultValue = "") String putforwardName,
                                          ModelMap modelMap, HttpServletRequest request){
        Pager<Putforward> pager = new Pager<Putforward>(pageIndex, pageSize);
        JSONObject param = new JSONObject();
        if("".equals(putforwardName)){
            param.put("putforwardName",putforwardName);
        }
        pager.setParams(param);
        pager = putforwardService.selectPager(pager);
        return setSuccessModelMap(modelMap,pager);
    }



}
