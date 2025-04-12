package com.yuke.springboot.util;

import java.security.MessageDigest;
import java.util.UUID;

public class UUIDHelper {
    private static final char[] DIGITS = new char[] {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f' };

    public static String getUuid() {
        try {
            UUID uuid = UUID.randomUUID();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(uuid.toString().getBytes());
            byte[] abResult = md5.digest();
            return byte2hex(abResult);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String digest(String s) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(s.getBytes());
            byte[] abResult = md5.digest();
            return byte2hex(abResult);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String byte2hex(byte[] data) {
        if (data == null)
            return null;
        return new String(encodeHex(data));
    }

    public static char[] encodeHex(byte[] data) {
        int l = data.length;
        char[] out = new char[l << 1];
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS[0xF & data[i]];
        }
        return out;
    }
}
