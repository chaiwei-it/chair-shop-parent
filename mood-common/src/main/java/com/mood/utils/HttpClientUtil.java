package com.mood.utils;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * 描述:
 *
 * @author chaiwei
 * @create 2018-10-15 上午9:59
 */
public class HttpClientUtil {


    public static String sendPost(String url, String json) {
        String returnValue = "这是默认返回值，接口调用失败";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        try {
            //第一步：创建HttpClient对象
            httpClient = HttpClients.createDefault();
            //第二步：创建httpPost对象
            HttpPost httpPost = new HttpPost(url);
            //第三步：给httpPost设置JSON格式的参数
            StringEntity requestEntity = new StringEntity(json, "utf-8");
            requestEntity.setContentEncoding("UTF-8");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setEntity(requestEntity);
            //第四步：发送HttpPost请求，获取返回值
            returnValue = httpClient.execute(httpPost, responseHandler); //调接口获取返回值时，必须用此方法
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //第五步：处理返回值
        return returnValue;
    }

    public static String sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static String accessKeySecret = "9aa683e985520374215beab081b45db5";

    public static final String appid = "wx9edff7d79255744d";

//    public static String accessKeySecret = "694057610a70e294674bbef8d7eea271";
//
//    public static final String appid = "wx11cb8cf109197bfd";

    public static final String messageUrl = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=";



//    public static void main(String[] args) {
//
//        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + accessKeySecret;
//        JSONObject jsonObject = JSON.parseObject(sendGet(url));
//        String token = jsonObject.getString("access_token");
//        System.out.println(token);
//        TemplateData templateData = new TemplateData();
//        templateData.setTouser("ocLiG5A5bfTfe-sONqh48sYDyzEk");//接收者
//        templateData.setTemplate_id("LrGEj20G0VKxTZUJk-8nZW56cku-vS83q-v1H2mh8gE");//模板ID
//        templateData.setForm_id("wx1514460749284002f948a8d41331509791");
//        Map<String, Template> map = new HashMap<String, Template>();
//        Template template = new Template();
//        template.setValue("顺丰");
//        map.put("keyword1", template);
//        map.put("keyword2", template);
//        templateData.setData(map);
//        String json = JSONObject.toJSONString(templateData);
//        System.out.println(sendPost(messageUrl + token, json));
//
//    }


}
