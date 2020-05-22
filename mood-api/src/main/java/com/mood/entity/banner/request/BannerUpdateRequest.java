package com.mood.entity.banner.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-06-04 下午16:00
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class BannerUpdateRequest implements Serializable {


    private String id;

    /**
     * 展示图片
     */
    private String imageUrl;

    /**
     * 关联类型
     */
    private Integer relation;

    /**
     * 关联内容
     */
    private String content;

    /**
     * 说明
     */
    private String notes;

    /**
     * 位置
     */
    private String keywords;

    /**
     * 排序
     */
    private Integer sortNum;


}