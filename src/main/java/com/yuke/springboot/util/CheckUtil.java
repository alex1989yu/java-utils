package com.yuke.springboot.util;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Collection;
import java.util.Map;

public class CheckUtil {

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
}
