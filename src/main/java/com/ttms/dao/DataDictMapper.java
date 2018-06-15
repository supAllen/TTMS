package com.ttms.dao;

import com.ttms.pojo.DataDict;
import com.ttms.pojo.DataDictExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataDictMapper {
    int countByExample(DataDictExample example);

    int deleteByExample(DataDictExample example);

    int deleteByPrimaryKey(Integer dictId);

    int insert(DataDict record);

    int insertSelective(DataDict record);

    List<DataDict> selectByExample(DataDictExample example);

    DataDict selectByPrimaryKey(Integer dictId);

    int updateByExampleSelective(@Param("record") DataDict record, @Param("example") DataDictExample example);

    int updateByExample(@Param("record") DataDict record, @Param("example") DataDictExample example);

    int updateByPrimaryKeySelective(DataDict record);

    int updateByPrimaryKey(DataDict record);

    List<DataDict> getPagingData(int page);
}