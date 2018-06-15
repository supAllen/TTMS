package com.ttms.serviceInterface;

import com.ttms.pojo.DataDict;
import com.ttms.pojo.Play;

import java.util.List;

/**
 * @Author: Allen
 * @Description:    剧目管理
 * @Date: Created in 16:19 2018/6/4
 * @Modify By:
 */
public interface playManager {

    /**
     * 添加剧目
     * @param play
     */
    void addplay(Play play);

    /**
     * 查询所有剧目
     * @return
     */
    List<Play> selectAll();

    /**
     * 根据类型查询
     * @param condition
     */
    List<Play> selectByCondition(String condition);

    /**
     * 下架
     * @param playId
     */
    void dropOff(Integer playId);

    /**
     * 统计票房
     * @param playId
     * @return
     */
    Integer boxOfficeStatistics(Integer playId);

    /**
     * 获取所有的电影类型和所有电影语言和电影状态选择
     * @return
     */
    List<List<DataDict>> getPlayTypesAndLanguages();
}
