package com.mood.controller.code;

import com.mood.base.BaseController;
import com.mood.code.service.CodeService;
import com.mood.common.HttpCode;
import com.mood.entity.rabate.Code;
import com.mood.controller.utils.CodeUtil;
import com.mood.controller.utils.FileUtil;
import com.mood.controller.utils.OssFileContent;
import com.mood.controller.utils.WeiXinUtil;
import com.mood.utils.IdGen;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import com.mood.entity.AccessToken;
import java.util.List;

/**
 * 模块
 * @author chaiwei
 * @time 2018-05-15 下午10:00
 */
@RestController
@RequestMapping("/api/{version}/code")
public class CodeApi extends BaseController {

    @Autowired
    private CodeService codeService;

    /**
     * 详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<ModelMap> select(@PathVariable(value = "id", required = false) String id,
                                           ModelMap modelMap, HttpServletRequest request){
        Code code = codeService.selectById(id);
        return setSuccessModelMap(modelMap,code);
    }

    /**
     * 创建
     * @param goodsId
     * @return
     */
    @GetMapping("")
    public ResponseEntity<ModelMap> selectByGoodsId(@RequestParam(value = "goodsId", required = false, defaultValue = "index") String goodsId,
                                                    ModelMap modelMap, HttpServletRequest request){
        String userId = getUserId(request);
        if(userId == null){
            return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
        }
        JSONObject param = new JSONObject();
        param.put("userId",userId);
        param.put("goodsId", goodsId);
        param.put("appType", 1);
        List<Code> list = codeService.selectAll(param);
        Code code = new Code();
        code.setId(IdGen.uuid());
        if(list.size() > 0){
            if(list.get(0).getCodeUrl() != null && !"".equals(list.get(0).getCodeUrl())){
                code = list.get(0);
                AccessToken accessToken = WeiXinUtil.getAccessToken();
                String pageUrl = "";
                String sceneStr = "";
                pageUrl = "pages/index/index";
                sceneStr = code.getId();
                String filePath = CodeUtil.getminiqrQr(sceneStr, accessToken.getToken(),pageUrl);
                if(!"".equals(filePath)) {
                    String fileUrl = FileUtil.OSSUpLoad(filePath, OssFileContent.CODEPATH);
                    code.setCodeUrl(fileUrl);
                    codeService.update(code);
                }
            }else{
                code = list.get(0);
            }
        }else{
            AccessToken accessToken = WeiXinUtil.getAccessToken();
            String pageUrl = "";
            String sceneStr = "";
            pageUrl = "pages/index/index";
            sceneStr = code.getId();
            String filePath = CodeUtil.getminiqrQr(sceneStr, accessToken.getToken(),pageUrl);
            if(!"".equals(filePath)){
                String fileUrl = FileUtil.OSSUpLoad(filePath, OssFileContent.CODEPATH);
                code.setUserId(userId);
                code.setGoodsId(goodsId);
                code.setCodeUrl(fileUrl);
                code.setAppType(1);
                codeService.insert(code);
            }
        }
        return setSuccessModelMap(modelMap, code);

    }
}
