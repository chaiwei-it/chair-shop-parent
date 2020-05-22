package com.mood.entity;

import lombok.Data;

import java.util.Map;

/**
 * 模块
 *
 * @author chaiwei
 * @time 2018-07-16 17:09
 */
@Data
public class Template {
    private String touser;//接收人的openId

    private String template_id;//模版id

    private String page;//点击模版访问url

    private String form_id;//消息头部颜色

    private Map<String,TemplateData> data;//消息内容

}
