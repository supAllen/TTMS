package com.ttms.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Allen
 * @Description:    字符串工具类
 * @Date: Created in 9:50 2018/6/7
 * @Modify By:
 */
public class StringSplitUtils {

    /**
     * 返回所有ID
     * @param ids
     * @return
     */
    public static List<Integer> getIds(String ids){
        System.out.println(ids);
        List<Integer> iids = new ArrayList<>();
        if (ids.length() == 1){
            iids.add(Integer.valueOf(ids));
            return iids;
        }
        String[] split = ids.split(",");
        iids = new ArrayList<>(split.length);
        for (String idstr : split){
            if (null != idstr){
                iids.add(Integer.valueOf(idstr));
            }
        }
        return iids;
    }

    public static List<Long> getIds(String ids,Class Type){
        List<Long> iids = new ArrayList<>();
        if (ids.length() == 1){
            iids.add(Long.valueOf(ids));
            return iids;
        }
        String[] split = ids.split(",");
        iids = new ArrayList<>(split.length);
        for (String idstr : split){
            if (null != idstr){
                iids.add(Long.valueOf(idstr));
            }
        }
        return iids;
    }

    public static void main(String[] args) {
        List<Integer> ids = getIds("1");
        System.out.println(ids.get(0));
    }
}
