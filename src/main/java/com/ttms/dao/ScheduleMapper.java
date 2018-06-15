package com.ttms.dao;

import com.ttms.pojo.Schedule;
import com.ttms.pojo.ScheduleExample;
import java.util.List;

import com.ttms.pojo.Seat;
import org.apache.ibatis.annotations.Param;

public interface ScheduleMapper {
    int countByExample(ScheduleExample example);

    int deleteByExample(ScheduleExample example);

    int deleteByPrimaryKey(Integer schedId);

    int insert(Schedule record);

    int insertSelective(Schedule record);

    List<Schedule> selectByExample(ScheduleExample example);

    Schedule selectByPrimaryKey(Integer schedId);

    int updateByExampleSelective(@Param("record") Schedule record, @Param("example") ScheduleExample example);

    int updateByExample(@Param("record") Schedule record, @Param("example") ScheduleExample example);

    int updateByPrimaryKeySelective(Schedule record);

    int updateByPrimaryKey(Schedule record);

    List<Schedule> selectSchByPlayId(Integer playId);

    int getMaxId();

}