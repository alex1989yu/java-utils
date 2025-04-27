package com.yuke.springboot.util;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

public class CheckUtil {
    private static final String[] images = new String[]{".jpg", ".gif", ".jpeg", ".png", ".bmg"};

    private static final String[] videos = new String[]{"avi","mp4","rm","rmvb"};

    private static final String[] audios = new String[]{"mp3"};

    /**
     * 判断是否为图片
     *
     * @param str
     * @return
     */
    public static boolean isImage(String str) {

        for (String e : images) {
            if (str.endsWith(e)) {
                return true;
            }
        }
        return false;
    }

    public static boolean notImage(String str) {
        return !isImage(str);
    }

    /**
     * 判断是否为视频
     *
     * @param str
     * @return
     */
    public static boolean isVideo(String str){
        for (String e : videos) {
            if (str.endsWith(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否为音频
     *
     * @param str
     * @return
     */
    public static boolean isAudios(String str){
        for (String e : audios) {
            if (str.endsWith(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断集合类为空
     *
     * @param c
     * @return
     */
    public static boolean isEmpty(Collection<?> c) {
        return null == c || c.size() == 0;
    }

    /**
     * 判断数组为空
     *
     * @param arr
     * @return
     */
    public static boolean isEmpty(Object[] arr) {
        return ArrayUtils.isEmpty(arr);
    }

    /**
     * 判断集合不为空
     *
     * @param c
     * @return
     */
    public static boolean notEmpty(Collection<?> c) {

        return !isEmpty(c);
    }

    /**
     * 判断对象不为空
     *
     * @param c
     * @return
     */
    public static boolean notEmpty(Object c) {

        return !isEmpty(c);
    }

    /**
     * 判断数组不为空
     *
     * @param c
     * @return
     */
    public static boolean notEmpty(Object[] c) {

        return !isEmpty(c);
    }

    /**
     * 判断Map不为空
     *
     * @param c
     * @return
     */
    public static boolean notEmpty(Map<?, ?> c) {

        return !isEmpty(c);
    }

    /**
     * 判断字符串不为空
     *
     * @param c
     * @return
     */
    public static boolean notEmpty(String c) {
        return !isEmpty(c);
    }

    /**
     * 判断字符串为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return null == str || str.trim().length() == 0;
    }

    /**
     * 判断对象为空
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        return null == obj;
    }

    public static boolean check(String str, String regex) {
        return Pattern.compile(regex).matcher(str).matches();
    }

    public static boolean isDateTime(String str) {

        return CheckUtilBAK.isEmpty(str) ? false
                : check(str,
                "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s((([0-1][0-9])|(2?[0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
    }

    public static boolean notDateTime(String str) {
        return !isDateTime(str);
    }

    public static boolean isDate(String str) {

        return CheckUtilBAK.isEmpty(str) ? false
                : check(str,
                "^((((19|20)\\d{2})-(0?(1|[3-9])|1[012])-(0?[1-9]|[12]\\d|30))|(((19|20)\\d{2})-(0?[13578]|1[02])-31)|(((19|20)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|((((19|20)([13579][26]|[2468][048]|0[48]))|(2000))-0?2-29))$");
    }

    public static boolean notDate(String str) {
        return !isDateTime(str);
    }
}
