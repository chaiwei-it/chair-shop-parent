package com.mood.controller.rebate;

import com.mood.base.BaseController;
import com.mood.base.Pager;
import com.mood.common.HttpCode;
import com.mood.entity.rabate.Rebate;
import com.mood.rebate.service.RebateService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * 模块
 * @author chaiwei
 * @time 2018-05-15 下午10:00
 */
@RestController
@RequestMapping("/api/{version}/rebate")
public class RebateApi extends BaseController {

    @Autowired
    private RebateService rebateService;

    /**
     * 添加
     * @param orderId
     * @return
     */
    @PostMapping("")
    public ResponseEntity<ModelMap> create(@RequestParam(value = "orderId", required = false, defaultValue = "") String orderId,
                                           ModelMap modelMap, HttpServletRequest request){
        Boolean result = rebateService.orderRebate(orderId);
        if(result){
            return setSuccessModelMap(modelMap);
        }
        return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * 修改
     * @param rebate
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<ModelMap> update(@PathVariable(value = "id", required = false) String id,
                          @ModelAttribute Rebate rebate,
                          ModelMap modelMap, HttpServletRequest request){
        rebate.setId(id);
        Integer result = rebateService.update(rebate);
        if(result > 0){
            return setSuccessModelMap(modelMap,rebate);
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
        Integer result = rebateService.deleteById(id);
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
        Rebate rebate = rebateService.selectById(id);
        return setSuccessModelMap(modelMap,rebate);
    }

    /**
     * 列表
     * @param
     * @return
     */
    @GetMapping("")
    public ResponseEntity<ModelMap> list(ModelMap modelMap, HttpServletRequest request){
        JSONObject param = new JSONObject();

        List<Rebate> list = rebateService.selectAll(param);
        return setSuccessModelMap(modelMap,list);
    }

    /**
     * 分页
     * @param pageIndex
     * @param pageSize
     * @param rebateName
     * @return
     */
    @GetMapping("pager")
    public ResponseEntity<ModelMap> pager(@RequestParam(value = "pageIndex",required = false,  defaultValue = "1") Integer pageIndex,
                                          @RequestParam(value = "pageSize",required = false,  defaultValue = "20") Integer pageSize,
                                          @RequestParam(value = "rebateName", required = false, defaultValue = "") String rebateName,
                                          ModelMap modelMap, HttpServletRequest request){
        Pager<Rebate> pager = new Pager<Rebate>(pageIndex, pageSize);
        JSONObject param = new JSONObject();
        if("".equals(rebateName)){
            param.put("rebateName",rebateName);
        }
        pager.setParams(param);
        pager = rebateService.selectPager(pager);
        return setSuccessModelMap(modelMap,pager);
    }

    /**
     * 条件查询
     * @param
     * @return
     */
    @GetMapping("getRebatePrice")
    public ResponseEntity<ModelMap> getRebatePrice(
            ModelMap modelMap, HttpServletRequest request){
        String userId = getUserId(request);
        if(userId == null){
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        }
        Map<String,Double> map = new HashMap<String,Double>();
        JSONObject param = new JSONObject();
        param.put("userId", userId);
        param.put("status", 1);
        param.put("rebateNum", "0");
        List<Rebate> list = rebateService.selectAll(param);
        Double todayPrice = 0.0;
        for(Rebate rebate: list){
            todayPrice += rebate.getRabatePrice().doubleValue();
        }
        map.put("todayPrice", todayPrice);
        param.put("rebateNum", "1");
        list = rebateService.selectAll(param);
        Double toMonthPrice = 0.0;
        for(Rebate rebate: list){
            toMonthPrice += rebate.getRabatePrice().doubleValue();
        }
        map.put("toMonthPrice", toMonthPrice);
        param.put("rebateNum", "2");
        list = rebateService.selectAll(param);
        Double totalPrice = 0.0;
        for(Rebate rebate: list){
            totalPrice += rebate.getRabatePrice().doubleValue();
        }
        map.put("totalPrice", totalPrice);
        JSONObject estimateParam = new JSONObject();
        estimateParam.put("userId", userId);
        estimateParam.put("status", 0);
        list = rebateService.selectAll(estimateParam);
        Double estimatePrice = 0.0;
        for(Rebate rebate: list){
            estimatePrice += rebate.getRabatePrice().doubleValue();
        }
        map.put("estimatePrice", estimatePrice);
        return setSuccessModelMap(modelMap,map);
    }

    /**
     * 条件查询
     * @param
     * @return
     */
    @GetMapping("getRebateList")
    public ResponseEntity<ModelMap> getRebateList(
            @RequestParam(value = "rebateNum", required = false, defaultValue = "2") String rebateNum,
            @RequestParam(value = "rebateType", required = false, defaultValue = "1") Integer rebateType,
            ModelMap modelMap, HttpServletRequest request){
        String userId = getUserId(request);
        if(userId == null){
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        }
        JSONObject param = new JSONObject();
        param.put("rebateNum", rebateNum);
        param.put("status", 1);
        param.put("userId", userId);
        param.put("rebateType", rebateType);
        List<Rebate> list = rebateService.selectAll(param);
        Map<String, Rebate> map = new HashMap<String, Rebate>();
        for(Rebate rebate: list){
            if(map.get(rebate.getOrderId()) != null) {
                rebate.setRabatePrice(rebate.getRabatePrice().add(map.get(rebate.getOrderId()).getRabatePrice()));
            }
            map.put(rebate.getOrderId(), rebate);
        }
        list = new ArrayList<Rebate>();
        Iterator iter = map.entrySet().iterator(); // 获得map的Iterator
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            list.add((Rebate)entry.getValue());
        }
        return setSuccessModelMap(modelMap,list);
    }

    /**
     * 条件查询
     * @param
     * @return
     */
    @GetMapping("getRebate")
    public ResponseEntity<ModelMap> getRebate(
            @RequestParam(value = "orderId", required = false, defaultValue = "") String orderId,
            ModelMap modelMap, HttpServletRequest request){
        String userId = getUserId(request);
        if(userId == null){
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        }
        JSONObject param = new JSONObject();
        param.put("userId", userId);
        param.put("orderId", orderId);
        List<Rebate> list = rebateService.selectAll(param);
        return setSuccessModelMap(modelMap,list);
    }

    /**
     * 获取预计红包
     * @param
     * @return
     */
    @GetMapping("getEstimateList")
    public ResponseEntity<ModelMap> getEstimateList(
            ModelMap modelMap, HttpServletRequest request){
        String userId = getUserId(request);
        if(userId == null){
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        }
        JSONObject param = new JSONObject();
        param.put("status", 0);
        param.put("userId", userId);
        List<Rebate> list = rebateService.selectAll(param);
        return setSuccessModelMap(modelMap,list);
    }

}
