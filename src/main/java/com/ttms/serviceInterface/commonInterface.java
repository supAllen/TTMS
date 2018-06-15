package com.ttms.serviceInterface;

import java.util.List;

/**
 * @Author: Allen
 * @Description:    公有的接口
 * @Date: Created in 8:55 2018/6/7
 * @Modify By:
 */
public interface commonInterface<T> {
    void addd(T t);
    void del(String ids);
    void modifyy(T t);
    List<T> selectAll();
}
