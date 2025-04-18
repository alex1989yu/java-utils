package com.yuke.springboot.util;

/**
 * 字符串操作工具类
 */
public class StringUtils {
    public static boolean isNumber(String str){
        if (str == null || str.isEmpty()) {
            return false;
        }
        char c = str.charAt(0);
        if ('+' != c && '-' != c && !Character.isDigit(c)) {
            return false;
        }
        for (int i = 1; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isMobile(String phone, String country) {
        if (phone == null || phone.isEmpty()) {
            return false;
        }
        if (country == null || country.isEmpty() || country.equals("001")) {
            if (phone.startsWith("1") && phone.length() == 11) {
                for (int i = 1; i <= 10; ++i) {
                    char c = phone.charAt(i);
                    if (!(c >= '0' && c <= '9')) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
