package com.yuke.springboot.util;

import java.nio.charset.Charset;
import java.util.Base64;

public class Codec {
    /**
     * 将字符串进行Base64编码
     *
     * @param str
     * @return 返回字节流
     */
    public static byte[] encode(String str) {
        byte[] bytes = Base64.getEncoder().encode(str.getBytes(Charset.forName("UTF-8")));
        return bytes;
    }

    /**
     * 将字节流进行Base64编码
     *
     * @param bytes
     * @return 返回字符串
     */
    public static String encodeToString(byte[] bytes) {
        String str = Base64.getEncoder().encodeToString(bytes);
        return str;
    }

    /**
     * 必须配合encode使用，用于encode编码之后解码
     *
     * @param str 字符串
     * @return 返回字节流
     */
    public static byte[] decode(String str) {
        byte[] bytes = Base64.getDecoder().decode(str);
        return bytes;
    }

    /**
     * 必须配合encode使用，用于encode编码之后解码
     *
     * @param input 字节流
     * @return 返回字节流
     */
    public static byte[] decode(byte[] input) {
        byte[] bytes = Base64.getDecoder().decode(input);
        return bytes;
    }
}
