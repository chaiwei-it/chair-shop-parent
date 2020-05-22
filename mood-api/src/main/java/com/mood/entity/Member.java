//package com.mood.entity;
//
//import lombok.Data;
//
//import javax.persistence.Column;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import java.math.BigDecimal;
//import java.sql.Timestamp;
//
///**
// * 项目名称：leimingtech-entity
// * 类名称：Member
// * 类描述：会员实体类
// * 下午1:17:51 修改备注：
// *
// */
//@Data
//@Table(name = "shop_member")
//public class Member  {
//
//	@Id
//	private String memberId;
//
//	/**
//	 * 会员名称
//	 */
//	private String memberName;
//
//	/**
//	 * 真实姓名
//	 */
//	private String memberTruename;
//
//	/**
//	 * 会员头像
//	 */
//	private String memberAvatar;
//
//	/**
//	 * 会员性别（0:保密，1：女，2：男）
//	 */
//	private Integer memberSex;
//
//	/**
//	 * 会员生日
//	 */
//	private Long memberBirthday;
//
//	/**
//	 * 密码
//	 */
//	private String paymentPasswd;
//
//	/**
//	 * 支付密码
//	 */
//	private String memberPasswd;
//
//	/**
//	 * 邮箱
//	 */
//	private String memberEmail;
//
//	/**
//	 * QQ
//	 */
//	private String memberQq;
//
//	/**
//	 * 阿里旺旺
//	 */
//	private String memberWw;
//
//	/**
//	 * 登录次数
//	 */
//	private Integer memberLoginNum;
//
//	/**
//	 * 会员注册时间
//	 */
//	private Long createTime;
//
//	/**
//	 * 当前登录时间
// 	 */
//	private Long memberLoginTime;
//
//	/**
//	 * 上次登录时间
//	 */
//	private Long memberOldLoginTime;
//
//	/**
//	 * 当前登录ip
//	 */
//	private String memberLoginIp;
//
//	/**
//	 * 上次登录ip
//	 */
//	private String memberOldLoginIp;
//
//	/**
//	 * qq互联id
//	 */
//	private String memberUnionid;
//
//	/**
//	 * qq互联id
//	 */
//	private String memberOpenid;
//
//	/**
//	 * qq账号相关信息
//	 */
//	private String memberInfo;
//
//	/**
//	 * 会员等级积分
//	 */
//	private Integer memberRankPoints;
//
//	/**
//	 * 会员消费积分
//	 */
//	private Integer memberConsumePoints;
//
//	/**
//	 * 预存款可用金额
//	 */
//	private BigDecimal availablePredeposit;
//
//	/**
//	 * 预存款冻结金额
//	 */
//	private BigDecimal freezePredeposit;
//
//	/**
//	 * 是否允许举报(1可以/2不可以)
//	 */
//	private Integer informAllow;
//
//	/**
//	 * 会员是否有购买权限 1为开启 0为关闭
//	 */
//	private Integer isBuy;
//
//	/**
//	 * 会员是否有咨询和发送站内信的权限 1为开启 0为关闭
//	 */
//	private Integer isAllowtalk;
//
//	/**
//	 * 会员的开启状态 1为开启 0为关闭
//	 */
//	private Integer memberState;
//
//	/**
//	 * 会员信用
//	 */
//	private Integer memberCredit;
//
//	/**
//	 * sns空间访问次数
//	 */
//	private Integer memberSnsvisitnum;
//
//	/**
//	 * 地区ID
//	 */
//	private String memberAreaid;
//
//	/**
//	 * 城市ID
//	 */
//	private String memberCityid;
//
//	/**
//	 * 省份ID
//	 */
//	private String memberProvinceid;
//
//	/**
//	 * 地区内容
//	 */
//	private String memberAreainfo;
//
//	/**
//	 * 隐私设定
//	 */
//	private String memberPrivacy;
//
//	private Integer isDel;
//
//	/**
//	 * 登录类型：1、qq 2､sina 3、weixin
//	 */
//    private String signCode;
//
//    private Integer signCodeState;
//
//	/**
//	 * 手机号
//	 */
//	private String memberMobile;
//
//	/**
//	 * 会员等级
//	 */
//	private String memberGradeid;
//
//	/**
//	 * 用户类型
//	 */
//	private String memberType;
//
//	/**
//	 * 扩展字段用于存放未读信息条数
//	 */
//
//	/**
//	 * 会员升级日期
//	 */
//	@Column(name="gradeDate ")
//	private Long gradeDate;
//
//	/**
//	 * 会员升级日期
//	 */
//
//	/**
//	 *来自那个平台注册 0pc ，1安卓 ，2ios
//	 */
//	private int memberIdentification;
//
//	/**
//	 * 第三方注册默认0 本站，1微信，2QQ，3新浪
//	 */
//	private int memberThirdParty;
//
//	/**
//	 * 临时字段用来存注册百分比
//	 */
//
//	/**
//	 * 邀请码
//	 */
//	private String memberInvitationCode;
//
//	/**
//	 * 邀请人编码
//	 */
//	private String memberInvitationCodeFrom;
//
//	/**
//	 * 邀请人
//	 */
//	private String memberFrom;
//
//	/**
//	 * 会员角色：1：会员，2：店铺和会员
//	 */
//	private String memberRole;
//
//}