package com.mood.entity.specValue.request;

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
public class SpecValueInsertRequest implements Serializable {

    /**
     * 名称
     */
    @NotNull(message = "请输入昵称")
    @NotBlank(message = "请输入昵称")
    private String name;

    private Integer showStatus;

    private Integer sortNum;

    private String image;

    private String specId;


}