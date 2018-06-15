package com.ttms.utils;

import java.util.Date;
import java.util.UUID;

/**
 * @Author: Allen
 * @Description:
 * @Date: Created in 17:58 2018/6/11
 * @Modify By:
 */
public class NameUtils {
    public static String getName(){
        return UUID.randomUUID().toString().substring(0,6);
    }
}
