package com.wordbreak.util;

public class StrUtil {
    /**
     * 功能：格式化空字符串
     *
     * @param str
     * @return String
     */
    public static String formatNull(Object str) {
        return null == str || "null".equals(str) ? "" : str.toString();
    }
}
