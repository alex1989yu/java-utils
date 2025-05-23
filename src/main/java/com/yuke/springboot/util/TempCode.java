package com.yuke.springboot.util;

import java.util.Random;

/**
 * 随机ID
 */
public class TempCode {
    /**
     * 生成随机串
     * @param length
     * @return
     */
    public static String generate(int length)
    {
    	StringBuffer ids = new StringBuffer("");
        String[] members =
        {
                "1","2","3","4","5","6","7","8","9","0","a","b","c","d","e","f",
                "g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v",
                "w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L",
                "M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"
        };
        Random rd = new Random();
        for(int i = 0;i < length;i++)
        {
            int j = rd.nextInt(members.length);
            ids.append(members[j]);
        }
        return ids.toString();
    }
}
