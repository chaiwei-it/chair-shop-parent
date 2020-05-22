package com.mood.entity.user.response;

import lombok.Data;

/**
 * 内容
 *
 * @author chaiwei
 * @time 2018-06-08 22:45
 */
@Data
public class UserInfo {
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户头像
     */
    private String headImage;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;
}
