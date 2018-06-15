package com.ttms.service;

import com.ttms.dao.DataDictMapper;
import com.ttms.pojo.DataDict;
import com.ttms.pojo.DataDictExample;
import com.ttms.serviceInterface.commonInterface;
import com.ttms.utils.StringSplitUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Allen
 * @Description: 字典管理
 * @Date: Created in 12:10 2018/6/7
 * @Modify By:
 */
@Service
public class dictService implements commonInterface<DataDict> {

    @Autowired
    private DataDictMapper dataDictMapper;

    @Override
    public void addd(DataDict dataDict) {
        dataDictMapper.insert(dataDict);
    }

    @Override
    public void del(String ids) {
        List<Integer> list = StringSplitUtils.getIds(ids);
        DataDictExample example = new DataDictExample();
        DataDictExample.Criteria criteria = example.createCriteria();
        criteria.andDictIdIn(list);
        dataDictMapper.deleteByExample(example);
    }

    @Override
    public void modifyy(DataDict dataDict) {
        DataDictExample de = new DataDictExample();
        DataDictExample.Criteria criteria = de.createCriteria();
        criteria.andDictIdEqualTo(dataDict.getDictId());
        dataDictMapper.updateByExample(dataDict, de);
    }

    @Override
    public List<DataDict> selectAll() {
        return dataDictMapper.selectByExample(null);
    }

    /**
     * 在添加数据字典的时候进行查询返回
     * 获取全部的根
     * 总共只有两层，就返回两个list集合
     * @return
     */
    public List<List<String>> getDictTypes() {
        // 获取根，并将value字段封装进String类型的集合中
        List<List<String>> values = new ArrayList<>();
        DataDictExample de = new DataDictExample();
        DataDictExample.Criteria criteria = de.createCriteria();
        criteria.andDictValueEqualTo("根");
        List<DataDict> dataDicts = dataDictMapper.selectByExample(de);
        List<String> root = new ArrayList<>();
        root.add(dataDicts.get(0).getDictValue());
        values.add(root);

        // 获取二层结点，并将value字段封装进String类型的集合中
        DataDictExample dde = new DataDictExample();
        DataDictExample.Criteria criteria1 = dde.createCriteria();
        criteria1.andDictParentIdEqualTo(dataDicts.get(0).getDictId());
        List<DataDict> dataDicts1 = dataDictMapper.selectByExample(dde);
        List<String> others = new ArrayList<>();
        for (DataDict dd : dataDicts1) {
            others.add(dd.getDictValue());
        }
        values.add(others);
        return values;
    }
}
