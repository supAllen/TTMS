package com.ttms.service;

import com.ttms.dao.PlayMapper;
import com.ttms.dao.ScheduleMapper;
import com.ttms.dao.StudioMapper;
import com.ttms.dao.TicketMapper;
import com.ttms.pojo.*;
import com.ttms.serviceInterface.commonInterface;
import com.ttms.serviceInterface.ticketManager;
import com.ttms.utils.StringSplitUtils;
import com.ttms.utils.TaotaoResult;
import com.ttms.utils.resultType.schInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Allen
 * @Description: 演出计划管理
 * @Date: Created in 10:03 2018/6/6
 * @Modify By:
 */
@Service
public class scheduleService implements commonInterface<Schedule> {

    @Autowired
    private ScheduleMapper sm;
    @Autowired
    private PlayMapper pm;
    @Autowired
    private StudioMapper studioMapper;
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private ticketManager ts;
/*
    @Value("${SCH_MAP}")
    private String SCH_MAP;

    @Autowired
    private JedisClient jedisClient;*/

    /**
     * 添加一个演出计划
     * 步骤
     * 1 冲突检测（即开始时间上对应演出厅观众是否在观看）
     *      演出计划是否冲突
     *      演出厅是否冲突
     * 2 批量生成票
     *
     * @param schedule
     */
    public TaotaoResult addSch(Schedule schedule) {
        /**
         * 这里查看演出厅是否冲突
         */
        Integer studioId = schedule.getStudioId();
        Studio studio = studioMapper.selectByPrimaryKey(studioId);
        boolean studioisok = studio.getStudioFlag() == 0;
        // 因为管理员只有一个，然后演出计划可以在一段时间内存在多个，因为有多个演出厅
        if (studioisok) {
            // 不存在就直接创建就好了
            sm.insert(schedule);
            // 这里核心工作就做完了，就可以返回了
            // 批量生成票
            int maxId = sm.getMaxId();
            ts.batchAddTicket(maxId,studioId,studio);
            return TaotaoResult.ok();
        } else {
            return TaotaoResult.build(400, "添加演出计划失败", null);
        }
    }

    @Override
    public void addd(Schedule schedule) {
        throw new IllegalArgumentException();
    }

    @Override
    public void del(String ids) {
        if (ids.length() == 1){
            sm.deleteByPrimaryKey(Integer.valueOf(ids));
            ts.del(Integer.valueOf(ids));
            return;
        }
        List<Integer> list = StringSplitUtils.getIds(ids);
        ScheduleExample scheduleExample = new ScheduleExample();
        ScheduleExample.Criteria criteria = scheduleExample.createCriteria();
        criteria.andSchedIdIn(list);
        sm.deleteByExample(scheduleExample);
        // 批量删除票
        ts.del(list);
    }

    @Override
    public void modifyy(Schedule schedule) {
        ScheduleExample example = new ScheduleExample();
        ScheduleExample.Criteria criteria = example.createCriteria();
        criteria.andSchedIdEqualTo(schedule.getSchedId());
        sm.updateByExample(schedule, example);
    }

    @Override
    public List<Schedule> selectAll() {
        return sm.selectByExample(null);
    }

    /**
     * 根据剧目ID查询对应的当天的演出计划
     * @param playId
     * @return
     */
    public List<Schedule> selectByPlayIdGetNowDayData(int playId) {
        // 先查询缓冲中，没有再查数据库
        /*try {
            String data = jedisClient.hget(SCH_MAP, playId + "");
            if (null != data)
                return JsonUtils.jsonToList(data,Schedule.class);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        // 查数据库,获取此时以后的数据
        List<Schedule> schedules = sm.selectSchByPlayId(playId);
        // 添加到缓存中
        /*try {
            String json = JsonUtils.objectToJson(schedules);
            jedisClient.hset(SCH_MAP,playId+"",json);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return schedules;
    }

    public schInfo getschInfo(){
        schInfo schInfo = new schInfo();
        //获取非下架的电影
        PlayExample playExample = new PlayExample();
        PlayExample.Criteria criteria = playExample.createCriteria();
        criteria.andPlayStatusGreaterThan((byte) -1);
        List<Play> playList = pm.selectByExample(playExample);
        schInfo.setPlayList(playList);
        //获取没有被占用的演出厅
        StudioExample studioExample = new StudioExample();
        StudioExample.Criteria studioExampleCriteria = studioExample.createCriteria();
        studioExampleCriteria.andStudioFlagEqualTo((byte) 0);
        List<Studio> studios = studioMapper.selectByExample(studioExample);
        schInfo.setStudioList(studios);
        return schInfo;
    }

    public Integer getPlayTicketPrice(Integer pid){
        Play play = pm.selectByPrimaryKey(pid);
        return play.getPlayTicketPrice();
    }

    public Schedule getSch(Integer id){
        return sm.selectByPrimaryKey(id);
    }

    public List<Ticket> getTickets(Integer schid){
        TicketExample ticketExample = new TicketExample();
        TicketExample.Criteria ticketExampleCriteria = ticketExample.createCriteria();
        ticketExampleCriteria.andSchedIdEqualTo(schid);
        List<Ticket> tickets = ticketMapper.selectByExample(ticketExample);
        return tickets;
    }
}
