package com.ttms.service;

import com.ttms.dao.ScheduleMapper;
import com.ttms.dao.SeatMapper;
import com.ttms.dao.StudioMapper;
import com.ttms.jedis.JedisClient;
import com.ttms.pojo.Schedule;
import com.ttms.pojo.Seat;
import com.ttms.pojo.SeatExample;
import com.ttms.pojo.Studio;
import com.ttms.serviceInterface.seatManager;
import com.ttms.utils.StringSplitUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Allen
 * @Description:    座位管理
 * @Date: Created in 10:34 2018/6/7
 * @Modify By:
 */
@Service
public class seatService implements seatManager {

    @Autowired
    private SeatMapper seatMapper;
    @Autowired
    private ScheduleMapper scheduleMapper;
    /*@Autowired
    private StudioMapper studioMapper;

    @Value("${SEAT_MAP}")
    private String SEAT_MAP;
    @Autowired
    private JedisClient jedisClient;*/

    /**
     * 说明
     * 0 代表没有安排的座位
     * 1 代表已售出的座位
     * -1代表已损坏的座位
     *
     * 所以初始值都是0
     * @param seat
     */
    @Override
    public void addd(Seat seat) {
        seatMapper.insert(seat);
    }

    /**
     * 座位状态修改
     * @param ids
     */
    @Override
    public void del(String ids) {
        throw new IllegalArgumentException();
    }

    /**
     * 根据演出厅列表，批量删除座位
     * @param studioIds
     */
    @Async("seatExecutor")
    public void del(List<Integer> studioIds){
        SeatExample seatExample = new SeatExample();
        SeatExample.Criteria criteria = seatExample.createCriteria();
        criteria.andStudioIdIn(studioIds);
        seatMapper.deleteByExample(seatExample);
    }

    /**
     * 修改座位状态
     * 两种情况
     * 1 前端选票操作
     * 2 批量更新座位状态为可用状态
     */
    public void modifySeatStatus(String seatIds){
        List<Integer> ids = StringSplitUtils.getIds(seatIds);
        for (Integer id : ids){
            SeatExample seatExample = new SeatExample();
            SeatExample.Criteria criteria = seatExample.createCriteria();
            criteria.andSeatIdEqualTo(id);
            Seat seat = seatMapper.selectByPrimaryKey(id);
            // 1 表示已售出
            seat.setSeatStatus((byte) 1);
            seatMapper.updateByExample(seat,seatExample);
        }
    }

    @Override
    public void modifyy(Seat seat) {
        throw new IllegalArgumentException();
    }

    @Override
    public List<Seat> selectAll() {
        return seatMapper.selectByExample(null);
    }

    /**
     * 根据演出计划查询所有座位
     * 由于座位状态信息会频繁修改，所以就不加缓存了
     * @param schId
     * @return
     */
    public List<Seat> selectBySchIdAllSeat(Integer schId){
        // 先查询缓存
        /*try {
            String data = jedisClient.hget(SEAT_MAP, schId + "");
            if (null != data)
                return JsonUtils.jsonToList(data,Seat.class);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        // 没有则去数据库中查
        Schedule schedule = scheduleMapper.selectByPrimaryKey(schId);
        Integer studioId = schedule.getStudioId();
        // 根据演出厅ID查询出演出厅这场次的所有座位
        SeatExample seatExample = new SeatExample();
        SeatExample.Criteria criteria = seatExample.createCriteria();
        criteria.andStudioIdEqualTo(studioId);
        List<Seat> seats = seatMapper.selectByExample(seatExample);

        //添加到缓存中
        /*try {
            String json = JsonUtils.objectToJson(seats);
            jedisClient.hset(SEAT_MAP,schId+"",json);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        return seats;
    }

    /**
     * 批量生成座位
     * @param studio
     */
    @Async("seatExecutor")
    public void batchAdd(Studio studio) {
        System.out.println("seat 中"+Thread.currentThread());
        int rows = studio.getStudioRowCount();
        int colms = studio.getStudioColCount();
        int studioId = studio.getStudioId();
        List<Seat> list = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colms; j++) {
                Seat seat = new Seat();
                seat.setSeatRow(i);
                seat.setSeatColumn(j);
                seat.setStudioId(studioId);
                seat.setSeatStatus((byte) 1);
                list.add(seat);
            }
        }
        System.out.println(list);
        seatMapper.insertBatch(list);
    }

}
