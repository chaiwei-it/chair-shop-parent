package com.mood.controller.utils;

import com.mood.utils.IdGen;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 内容
 *
 * @author chaiwei
 * @time 2018-06-03 18:28
 */
public class CodeUtil {

//    public static void main(String[] args) {
//        String sceneStr = "test";
//        String accessToken = "10_0p5wfn67RxKLRZKKI4Gjg72Oeb_zMyMihqLgdXtw9endCfzTEbc3D9tsb_VAPrSKYLlRluBu0eiDhN9AsM82WizEiyvtWb9qizsbbb5lsz8wIZ1PTQM0zXGHh4_Tg47CFum7Q2ipiAGZM8JILDZaAHAPHD";
//        getminiqrQr(sceneStr, accessToken);
//    }

    public static String getminiqrQr(String sceneStr, String accessToken, String pageUrl) {
        String filePath = OssFileContent.FILEPATH + IdGen.uuid() + ".png";
        RestTemplate rest = new RestTemplate();
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+accessToken;
            Map<String,Object> param = new HashMap<>();
            param.put("scene", sceneStr);
            param.put("page", pageUrl);
            param.put("width", 430);
            param.put("auto_color", false);
            Map<String,Object> line_color = new HashMap<>();
            line_color.put("r", 0);
            line_color.put("g", 0);
            line_color.put("b", 0);
            param.put("line_color", line_color);
//            LOG.info("调用生成微信URL接口传参:" + param);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            HttpEntity requestEntity = new HttpEntity(param, headers);
            ResponseEntity<byte[]> entity = rest.exchange(url, HttpMethod.POST, requestEntity, byte[].class, new Object[0]);
//            System.out.println("调用小程序生成微信永久小程序码URL接口返回结果:" + entity.getBody().toString());
            byte[] result = entity.getBody();
//            LOG.info(Base64.encodeBase64String(result));
            inputStream = new ByteArrayInputStream(result);

            File file = new File(filePath);
            if (!file.exists()){
                file.createNewFile();
            }
            outputStream = new FileOutputStream(file);
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len = inputStream.read(buf, 0, 1024)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.flush();
        } catch (Exception e) {
            System.out.println("调用小程序生成微信永久小程序码URL接口异常"+e);
            return "";
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return filePath;
    }

}
