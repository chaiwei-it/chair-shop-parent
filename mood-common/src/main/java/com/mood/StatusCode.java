package com.mood;

import com.google.common.collect.ImmutableMap;

/**
 * @author Zhao Junjian
 */
public enum StatusCode implements RestStatus {

    OK(20000, "请求成功"),

    /**
     * 用户未登陆
     */
    USER_NOT_LOGIN(401, "用户未登陆"),

    // 40xxx 客户端不合法的请求
    INVALID_MODEL_FIELDS(40001, "字段校验非法"),

    /**
     * 参数类型非法，常见于SpringMVC中String无法找到对应的enum而抛出的异常
     */
    INVALID_PARAMS_CONVERSION(40002, "参数类型非法"),

    /**
     * http media type not supported
     */
    HTTP_MESSAGE_NOT_READABLE(41001, "HTTP消息不可读"),

    /**
     * 请求方式非法
     */
    REQUEST_METHOD_NOT_SUPPORTED(41002, "不支持的HTTP请求方法"),

    // 成功接收请求, 但是处理失败
    /**
     * Duplicate Key
     */
    DUPLICATE_KEY(42001, "操作过快, 请稍后再试"),

    /**
     * 用于注册时用户已经存在的情况
     */
    USER_EXISTS(42002, "用户已经存在, 请尝试登录"),

    /**
     * 用于登录时用户不存在的情况
     */
    USER_NOT_EXISTS(42003, "用户不存在, 请先注册"),

    /**
     * 凭证错误
     */
    INVALID_CREDENTIAL(42004, "用户名或密码错误"),

    /**
     * 用户余额不足
     */
    INSUFFICIENT_BALANCE(42005, "用户余额不足"),



    /**
     * 邮箱验证次数过多
     */
    MAIL_LIMIT(43000, "验证次数过多,稍后再试"),

    /**
     * 邮箱验证次数过多
     */
    MAIL_VERIFY_ERROR(43001, "邮箱验证失败"),

    // 50xxx 服务端异常
    /**
     * 用于处理未知的服务端错误
     */
    SERVER_UNKNOWN_ERROR(50001, "服务端异常, 请稍后再试"),

    /**
     * 用户管理
     */
    USER_NEVER(100001, "该用户不存在"),

    PARENT_NEVER(100002, "该上级ID不存在"),

    /**
     * 商品管理
     */
    NOT_STOCK(110001, "库存不足"),

    ;

    private final int code;

    private final String message;

    private static final ImmutableMap<Integer, StatusCode> CACHE;

    static {
        final ImmutableMap.Builder<Integer, StatusCode> builder = ImmutableMap.builder();
        for (StatusCode statusCode : values()) {
            builder.put(statusCode.code(), statusCode);
        }
        CACHE = builder.build();
    }

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static StatusCode valueOfCode(int code) {
        final StatusCode status = CACHE.get(code);
        if (status == null) {
            throw new IllegalArgumentException("No matching constant for [" + code + "]");
        }
        return status;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }

}
