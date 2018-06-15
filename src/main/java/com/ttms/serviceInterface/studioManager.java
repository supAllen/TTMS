package com.ttms.serviceInterface;

import com.ttms.pojo.Studio;

import java.util.List;

/**
 * @Author: Allen
 * @Description:    演出厅管理
 * @Date: Created in 16:30 2018/6/4
 * @Modify By:
 */
public interface studioManager {
    /**
     * 添加演出厅
     * 步骤：
     * 1 批量生成座位
     * @param studio
     */
    void addStudio(Studio studio);

    /**
     * 查询所有演出厅
     * @return
     */
    List<Studio> selectAll();

    /**
     * 根据ID更新演出厅状态
     * @param studioId
     */
    void modifyStatus(Integer studioId);
}
