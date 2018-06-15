package com.ttms.dao;

import com.ttms.pojo.Studio;
import com.ttms.pojo.StudioExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudioMapper {
    int countByExample(StudioExample example);

    int deleteByExample(StudioExample example);

    int deleteByPrimaryKey(Integer studioId);

    int insert(Studio record);

    int insertSelective(Studio record);

    List<Studio> selectByExample(StudioExample example);

    Studio selectByPrimaryKey(Integer studioId);

    int updateByExampleSelective(@Param("record") Studio record, @Param("example") StudioExample example);

    int updateByExample(@Param("record") Studio record, @Param("example") StudioExample example);

    int updateByPrimaryKeySelective(Studio record);

    int updateByPrimaryKey(Studio record);

    int getMaxId();
}