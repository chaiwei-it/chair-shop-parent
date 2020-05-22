package com.mood.controller.image;

import com.mood.base.BaseController;
import com.mood.base.Pager;
import com.mood.common.HttpCode;
import com.mood.entity.rabate.Image;
import com.mood.image.service.ImageService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 模块
 * @author chaiwei
 * @time 2018-05-15 下午10:00
 */
@RestController
@RequestMapping("/api/{version}/image")
public class ImageApi extends BaseController {

    @Autowired
    private ImageService imageService;

    /**
     * 添加
     * @param
     * @return
     */
    @PostMapping("")
    public ResponseEntity<ModelMap> create(@RequestParam(value = "num",required = false,  defaultValue = "0") Integer num,
                                           @RequestParam(value = "goodsId",required = false,  defaultValue = "") String goodsId,
                                           @RequestParam(value = "spec",required = false,  defaultValue = "") String spec,
                                           ModelMap modelMap, HttpServletRequest request){
        String userId = getUserId(request);
        if(userId == null){
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        }
        Image image = new Image();
        Integer result = imageService.insert(image);
        if(result > 0){
            return setModelMap(modelMap,HttpCode.CART_ADD_SUCCESS);
        }
        return setModelMap(modelMap, HttpCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * 修改
     * @param num
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<ModelMap> update(@PathVariable(value = "id", required = false) String id,
                                           @RequestParam(value = "num",required = false,  defaultValue = "0") Integer num,
                          ModelMap modelMap, HttpServletRequest request){
        String userId = getUserId(request);
        if(userId == null){
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        }
        Image image = new Image();
        if(num == 0){
            return setModelMap(modelMap, HttpCode.CART_NUM_LITTLE);
        }
        image.setId(id);
        Integer result = imageService.update(image);
        if(result > 0){
            return setSuccessModelMap(modelMap,image);
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
        String userId = getUserId(request);
        if(userId == null){
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        }
        Integer result = imageService.deleteById(id);
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
        String userId = getUserId(request);
        if(userId == null){
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        }
        Image image = imageService.selectById(id);
        return setSuccessModelMap(modelMap,image);
    }

    /**
     * 列表
     * @param name
     * @return
     */
    @GetMapping("")
    public ResponseEntity<ModelMap> list(@RequestParam(value = "name", required = false, defaultValue = "") String name,
                           ModelMap modelMap, HttpServletRequest request){
        String userId = getUserId(request);
        if(userId == null){
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        }
        JSONObject param = new JSONObject();
        if(!"".equals(name)){
            param.put("name",name);
        }
        List<Image> list = imageService.selectAll(param);
        return setSuccessModelMap(modelMap,list);
    }

    /**
     * 分页
     * @param pageIndex
     * @param pageSize
     * @param imageName
     * @return
     */
    @GetMapping("pager")
    public ResponseEntity<ModelMap> pager(@RequestParam(value = "pageIndex",required = false,  defaultValue = "1") Integer pageIndex,
                                          @RequestParam(value = "pageSize",required = false,  defaultValue = "20") Integer pageSize,
                                          @RequestParam(value = "imageName", required = false, defaultValue = "") String imageName,
                                          ModelMap modelMap, HttpServletRequest request){
        Pager<Image> pager = new Pager<Image>(pageIndex, pageSize);
        JSONObject param = new JSONObject();
        if("".equals(imageName)){
            param.put("imageName",imageName);
        }
        pager.setParams(param);
        pager = imageService.selectPager(pager);
        return setSuccessModelMap(modelMap,pager);
    }


}
