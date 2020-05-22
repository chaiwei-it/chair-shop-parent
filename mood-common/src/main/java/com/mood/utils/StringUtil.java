package com.mood.utils;

import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author chaiwei
 * @time 2018-01-14 10:17
 */
public class StringUtil {

    public static final Pattern PATTERN = Pattern.compile("^[-\\+]?[\\d]*$");

    public static boolean isInteger(String str) {
        return PATTERN.matcher(str).matches();
    }
}
