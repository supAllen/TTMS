package com.ttms.dao;

import com.ttms.pojo.SaleItem;
import com.ttms.pojo.SaleItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SaleItemMapper {
    int countByExample(SaleItemExample example);

    int deleteByExample(SaleItemExample example);

    int deleteByPrimaryKey(Long saleItemId);

    int insert(SaleItem record);

    int insertSelective(SaleItem record);

    List<SaleItem> selectByExample(SaleItemExample example);

    SaleItem selectByPrimaryKey(Long saleItemId);

    int updateByExampleSelective(@Param("record") SaleItem record, @Param("example") SaleItemExample example);

    int updateByExample(@Param("record") SaleItem record, @Param("example") SaleItemExample example);

    int updateByPrimaryKeySelective(SaleItem record);

    int updateByPrimaryKey(SaleItem record);
}