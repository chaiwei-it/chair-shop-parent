package com.mood.entity;

import lombok.Data;

/**
 * 内容
 *
 * @author chaiwei
 * @time 2018-06-03 20:31
 */
@Data
public class AccessToken {

    private String token;

    private Integer expiresIn;

}
