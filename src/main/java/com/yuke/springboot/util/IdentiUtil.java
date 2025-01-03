package com.yuke.springboot.util;

import java.security.SecureRandom;
import java.util.UUID;

public class IdentiUtil {
    private static SecureRandom random = new SecureRandom();

    private IdentiUtil() {
    }

    /**
     * 封装JDK自带的UUID, 通过Random数字生成,中间有-分割.
     *
     * @return the string
     */
    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 封装JDK自带的UUID, 通过Random数字生成,中间无-分割.
     *
     * @return the string
     */
    public static String uuid2() {
        return uuid().replaceAll("-", "");
    }

    /**
     * 封装JDK自带的UUID, 通过Random数字生成,中间无-分割,大写字母.
     *
     * @return the string
     */
    public static String uuid3() {
        return uuid().replaceAll("-", "").toLowerCase();
    }

    /**
     * 使用SecureRandom随机生成Long.
     *
     * @return the long
     */
    public static long randomLong() {
        return random.nextLong();
    }
}
