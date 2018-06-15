package com.ttms.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Allen
 * @Description:
 * @Date: Created in 20:34 2018/6/13
 * @Modify By:
 */
public class dateFormatUtils {

    public static Date getDate(String time) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.parse(time);
    }
}
