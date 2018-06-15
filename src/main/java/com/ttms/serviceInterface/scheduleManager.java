package com.ttms.serviceInterface;

import com.ttms.pojo.Schedule;
import com.ttms.utils.TaotaoResult;

import java.util.List;

/**
 * @Author: Allen
 * @Description:   演出计划管理
 * @Date: Created in 16:46 2018/6/4
 * @Modify By:
 */
public interface scheduleManager {
    /**
     * 添加演出计划：
     * 步骤：
     * 1 冲突检测（即开始时间上对应演出厅观众是否在观看）
     * 2 没有冲突就批量生成对应演出厅的票
     * @param schedule
     */
    TaotaoResult addSch(Schedule schedule);

    /**
     * 演出计划的更新
     * @param schedule
     */
    void modifySch(Schedule schedule);

    /**
     * 查询所有演出计划
     * @return
     */
    List<Schedule> selectAll();

    /**
     * 删除若干演出计划
     * @param sids
     */
    void delSch(String sids);
}
