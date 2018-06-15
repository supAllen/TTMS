package com.ttms.service;

import com.ttms.dao.SaleMapper;
import com.ttms.pojo.Sale;
import com.ttms.pojo.SaleExample;
import com.ttms.serviceInterface.commonInterface;
import com.ttms.utils.StringSplitUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Allen
 * @Description:    订单管理
 * @Date: Created in 21:55 2018/6/7
 * @Modify By:
 */
@Service
public class saleService implements commonInterface<Sale> {

    @Autowired
    private SaleMapper saleMapper;

    @Override
    public void addd(Sale sale) {
        saleMapper.insert(sale);
    }

    @Override
    public void del(String ids) {
        List<Long> saleIds = StringSplitUtils.getIds(ids, null);
        SaleExample example = new SaleExample();
        SaleExample.Criteria criteria = example.createCriteria();
        criteria.andSaleIdIn(saleIds);
        saleMapper.deleteByExample(example);
    }

    /**
     * 其实就是修改订单状态
     * @param sale
     */
    @Override
    public void modifyy(Sale sale) {
        SaleExample saleExample = new SaleExample();
        SaleExample.Criteria criteria = saleExample.createCriteria();
        criteria.andSaleIdEqualTo(sale.getSaleId());
        saleMapper.updateByExample(sale,saleExample);
    }

    @Override
    public List<Sale> selectAll() {
        throw new IllegalArgumentException();
    }

    /**
     * 根据userID查询订单
     * @param uid
     * @return
     */
    public List<Sale> selectByUserId(Integer uid){
        SaleExample saleExample = new SaleExample();
        SaleExample.Criteria criteria = saleExample.createCriteria();
        criteria.andUserIdEqualTo(uid);
        return saleMapper.selectByExample(saleExample);
    }
}
