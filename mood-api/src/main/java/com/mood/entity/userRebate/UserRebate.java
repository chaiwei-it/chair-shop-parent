package com.mood.entity.userRebate;

import com.mood.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * rebate_user_rebate
 * @author 
 */
@Data
@Table(name="rebate_user_rebate")
public class UserRebate extends BaseEntity {

    @Id
    private String id;

//    /**
//     * 用户ID
//     */
//    private String userId;

    /**
     * 用户手机号
     */
    private String mobile;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户编号
     */
    private String userNum;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户头像
     */
    private String headImage;

    /**
     * 上级id
     */
    private String parentId;

    /**
     * 顶级id
     */
    private String topId;

    /**
     * 顶级id
     */
    private String topAgentId;

    /**
     * 所属总代
     */
    private Integer level;

    /**
     * 代理级别
     */
    private Integer grade;

    /**
     * 代理余额
     */
    private BigDecimal balance;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 修改时间
     */
    private String updateDate;

    private String unionid;


}