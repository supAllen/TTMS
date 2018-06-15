package com.ttms.dao;

import com.ttms.pojo.Play;
import com.ttms.pojo.PlayExample;
import java.util.List;

import com.ttms.utils.resultType.playPage;
import org.apache.ibatis.annotations.Param;

public interface PlayMapper {
    int countByExample(PlayExample example);

    int deleteByExample(PlayExample example);

    int deleteByPrimaryKey(Integer playId);

    int insert(Play record);

    int insertSelective(Play record);

    List<Play> selectByExample(PlayExample example);

    Play selectByPrimaryKey(Integer playId);

    int updateByExampleSelective(@Param("record") Play record, @Param("example") PlayExample example);

    int updateByExample(@Param("record") Play record, @Param("example") PlayExample example);

    int updateByPrimaryKeySelective(Play record);

    int updateByPrimaryKey(Play record);

    List<Play> getPrePageData(playPage pg);

    Play getPlayInfo(Integer pid);
}