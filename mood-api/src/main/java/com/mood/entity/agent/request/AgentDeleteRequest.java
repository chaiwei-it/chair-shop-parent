package com.mood.entity.agent.request;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 应用模块
 * @author chaiwei
 * @time 2018-06-04 下午16:00
 */
@Data
public class AgentDeleteRequest implements Serializable {

    /**
     * 名称
     */
    @NotNull(message = "请输入ID")
    @NotBlank(message = "请输入ID")
    private String id;


}