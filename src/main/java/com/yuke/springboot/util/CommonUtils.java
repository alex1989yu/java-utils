package com.yuke.springboot.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通用工具
 */
@Slf4j
public class CommonUtils {

    /**
     * 中文正则
     */
    private static Pattern ZHONGWEN_PATTERN = Pattern.compile("[\u4e00-\u9fa5]");

    /**
     * 文件名 正则字符串
     * 文件名支持的字符串：字母数字中文.-_()（） 除此之外的字符将被删除
     */
    private static String FILE_NAME_REGEX = "[^A-Za-z\\.\\(\\)\\-（）\\_0-9\\u4e00-\\u9fa5]";

    /**
     * java 判断字符串里是否包含中文字符
     * @param str
     * @return
     */
    public static boolean ifContainChinese(String str) {
        if(str.getBytes().length == str.length()){
            return false;
        }else{
            Matcher m = ZHONGWEN_PATTERN.matcher(str);
            if (m.find()) {
                return true;
            }
            return false;
        }
    }

    public static void mapSorts(List<Map<String, String>> map, String sortKey) {
        Collections.sort(map, new Comparator<Map<String, String>>() {
            @Override
            public int compare(Map<String, String> o1, Map<String, String> o2) {
                // TODO Auto-generated method stub
                if (o1.get(sortKey).compareTo(o2.get(sortKey)) > 0) {
                    return 1;
                }
                return -1;
            }
        });
    }
}
