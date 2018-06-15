package com.ttms.service;

import com.ttms.dao.StudioMapper;
import com.ttms.pojo.Studio;
import com.ttms.pojo.StudioExample;
import com.ttms.serviceInterface.commonInterface;
import com.ttms.serviceInterface.seatManager;
import com.ttms.utils.StringSplitUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Allen
 * @Description:    演出厅管理
 * @Date: Created in 9:46 2018/6/7
 * @Modify By:
 */
@Service
public class studioService implements commonInterface<Studio> {

    @Autowired
    private StudioMapper studioMapper;

    @Autowired
    private seatManager seats;

    /**
     * 添加演出厅
     * 步骤
     * 1 添加一个演出厅
     * 2 批量生成票
     *
     * @param studio
     */
    @Override
    public void addd(Studio studio) {
        studioMapper.insert(studio);
        // 没有获取ID！！！！
        int maxId = studioMapper.getMaxId();
        studio.setStudioId(maxId);
        // 批量生成座位
        seats.batchAdd(studio);
    }

    @Override
    public void del(String ids) {
        List<Integer> list = StringSplitUtils.getIds(ids);
        StudioExample example = new StudioExample();
        StudioExample.Criteria criteria = example.createCriteria();
        criteria.andStudioIdIn(list);
        studioMapper.deleteByExample(example);
        // 删除对应演出厅的座位
        seats.del(list);
    }

    @Override
    public void modifyy(Studio studio) {
        StudioExample se = new StudioExample();
        StudioExample.Criteria criteria = se.createCriteria();
        criteria.andStudioIdEqualTo(studio.getStudioId());
        studioMapper.updateByExample(studio, se);
    }

    @Override
    public List<Studio> selectAll() {
        return studioMapper.selectByExample(null);
    }
}
