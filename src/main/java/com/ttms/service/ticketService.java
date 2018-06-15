package com.ttms.service;

import com.ttms.dao.ScheduleMapper;
import com.ttms.dao.SeatMapper;
import com.ttms.dao.StudioMapper;
import com.ttms.dao.TicketMapper;
import com.ttms.pojo.*;
import com.ttms.serviceInterface.ticketManager;
import com.ttms.utils.StringSplitUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * @Author: Allen
 * @Description:    票的管理
 * @Date: Created in 11:27 2018/6/7
 * @Modify By:
 */
@Service
public class ticketService implements ticketManager {

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Autowired
    private SeatMapper seatMapper;

    @Autowired
    private StudioMapper studioMapper;

    @Value("${TICKET_TIME}")
    private Integer TICKET_TIME;

    @Override
    public void addd(Ticket ticket) {
        throw new IllegalArgumentException();
    }

    /**
     * 就是删除，给一个字符串进行删除
     * @param ids
     */
    @Override
    public void del(String ids) {
        List<Long> list = StringSplitUtils.getIds(ids,null);
        TicketExample example = new TicketExample();
        TicketExample.Criteria criteria = example.createCriteria();
        criteria.andTicketIdIn(list);
        ticketMapper.deleteByExample(example);
    }

    /**
     * 批量删除票
     * @param schId
     */
    @Async("ticketExecutor")
    public void del(Integer schId) {
        // 根据演出计划ID找到票ID
        TicketExample ticketExample = new TicketExample();
        TicketExample.Criteria criteria = ticketExample.createCriteria();
        criteria.andSchedIdEqualTo(schId);
        List<Ticket> ticketList = ticketMapper.selectByExample(ticketExample);
        List<Long> ticketIds = new ArrayList<>();
        for (Ticket ticket : ticketList){
            ticketIds.add(ticket.getTicketId());
        }
        criteria.andTicketIdIn(ticketIds);
        ticketMapper.deleteByExample(ticketExample);
    }

    /**
     * 批量删除票的第三个版本
     * @param schIds
     */
    @Async("ticketExecutor")
    public void del(List<Integer> schIds){
        TicketExample ticketExample = new TicketExample();
        TicketExample.Criteria criteria = ticketExample.createCriteria();
        criteria.andSchedIdIn(schIds);
        ticketMapper.deleteByExample(ticketExample);
    }

    @Override
    public void modifyy(Ticket ticket) {
        throw new IllegalArgumentException();
    }

    /**
     * 批量修改票的状态
     * @param ticketIds
     * @param status
     */
    private void modifyy(List<Long> ticketIds,Byte status) {
        for (Long id : ticketIds){
            TicketExample ticketExample=new TicketExample();
            TicketExample.Criteria criteria=ticketExample.createCriteria();
            criteria.andTicketIdEqualTo(id);
            Ticket ticket = ticketMapper.selectByPrimaryKey(id);
            ticket.setTicketStatus(status);
            ticketMapper.updateByExample(ticket,ticketExample);
        }
    }

    @Override
    public List<Ticket> selectAll() {
        return ticketMapper.selectByExample(null);
    }

    /**
     * 获取演出计划ID列表和座位列表
     * @return
     */
    public Map<Integer,List<Seat>> getSeatAndSch(){
        Map<Integer,List<Seat>> schs = new HashMap<>();
        // 演出计划ID列表
        List<Schedule> schedules = scheduleMapper.selectByExample(null);
        List<Integer> sids = new ArrayList<>();
        for (Schedule sd : schedules){
            //sids.add(sd.getSchedId());
            sids.add(sd.getStudioId());
        }
        // 演出计划对应的座位列表
        for (Integer sid : sids){
            SeatExample se = new SeatExample();
            SeatExample.Criteria criteria = se.createCriteria();
            criteria.andStudioIdEqualTo(sid);
            List<Seat> seats = seatMapper.selectByExample(se);
            schs.put(sid,seats);
        }
        return schs;
    }

    /**
     * 批量生成票
     * @param schId
     */
    @Async("ticketExecutor")
    public void batchAddTicket(Integer schId, Integer studioId, Studio studio){
        // 修改演出厅状态
        StudioExample studioExample = new StudioExample();
        StudioExample.Criteria studioExampleCriteria = studioExample.createCriteria();
        studioExampleCriteria.andStudioIdEqualTo(studioId);
        int flag = studio.getStudioFlag();
        flag = flag+1;
        studio.setStudioFlag((byte) flag);
        studioMapper.updateByExample(studio,studioExample);

        // 获取固定不变的信息
        // 根据演出计划ID可以获取演出厅ID
        Schedule schedule = scheduleMapper.selectByPrimaryKey(schId);
        Integer ticketPrice = schedule.getSchedTicketPrice();
        Byte ticketStatus = 0;

        // 根据演出厅ID可以获取可以使用的座位
        SeatExample se = new SeatExample();
        SeatExample.Criteria criteria = se.createCriteria();
        criteria.andStudioIdEqualTo(studioId);
        List<Seat> seats = seatMapper.selectByExample(se);

        List<Ticket> tickets = new ArrayList<>();
        // 然后将可以使用的座位来进行批量生成票
        for (Seat s : seats){
            Ticket ticket = new Ticket();
            ticket.setSchedId(schId);
            ticket.setSeatId(s.getSeatId());
            ticket.setTicketPrice(ticketPrice);
            ticket.setTicketStatus(ticketStatus);
            tickets.add(ticket);
        }
        ticketMapper.insertBatch(tickets);
    }

    /**
     * 传递过来的格式
     * 一个字符串  "5:5,5:6"  表示五排五座和五排六坐
     *
     * @return
     */
    public boolean ticketOrder(String seatInfo,Integer schId){
        // 先判断距离开场时间是否小于，小于则不能订购
        Instant instant = new Date().toInstant();
        Instant plus = instant.plus(TICKET_TIME, ChronoUnit.MINUTES);
        // 根据schId查询演出开始时间
        Schedule schedule = scheduleMapper.selectByPrimaryKey(schId);
        if (plus.compareTo(schedule.getSchedTime().toInstant()) == 1)
            return false;

        String[] seats = seatInfo.split(",");
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        List<Integer> seatIds = new ArrayList<>();
        // 获取座位信息
        for (String seat : seats){
            String[] position = seat.split(":");
            Integer row = Integer.valueOf(position[0]);
            Integer col = Integer.valueOf(position[1]);
            rows.add(row);
            cols.add(col);
            // 获取座位ID信息
            SeatExample seatExample = new SeatExample();
            SeatExample.Criteria criteria = seatExample.createCriteria();
            criteria.andSeatRowEqualTo(row);
            criteria.andSeatColumnEqualTo(col);
            Integer seatId = seatMapper.selectByExample(seatExample).get(0).getSeatId();
            seatIds.add(seatId);
        }

        // 此时需要对座位进行加锁
        // 设置票的上锁时间
        // 根据演出计划ID和座位ID就可以唯一确认一张票
        for (Integer seatId : seatIds){
            TicketExample ticketExample = new TicketExample();
            TicketExample.Criteria tc = ticketExample.createCriteria();
            tc.andSchedIdEqualTo(schId);
            tc.andSeatIdEqualTo(seatId);
            // 修改为上锁时间
            // 个人感觉，应该是在票还没有被付款时，都是可以买的，所以此时的上锁只是。。。感觉可以删
            Ticket ticket = ticketMapper.selectByExample(ticketExample).get(0);
            ticket.setTicketLockedTime(new Date());
            ticketMapper.updateByExample(ticket,ticketExample);
        }
        return true;
    }

    /**
     * 这才是真正的卖票方法
     * 应该是在数据库层面进行锁定
     * @return
     */
    public boolean ticketSales(String ticketIds){
        // 使用不变的字符串，使得不管谁访问都会去争用一个锁
        // 为了防止一票多卖，所以需要互斥访问,保证了只有一个人能买到票
        synchronized ("123"){
            // 去数据库中查询，如果票的状态是已卖出，就直接返回错误
            List<Long> ids = StringSplitUtils.getIds(ticketIds, null);
            for (Long id : ids){
                Ticket ticket = ticketMapper.selectByPrimaryKey(id);
                // 表示已卖出
                if (ticket.getTicketStatus() == 9)
                    return false;
            }

            // 再次检查是否接近电影上映的时间
            // 先判断距离开场时间是否小于，小于则不能订购
            Integer schedId = ticketMapper.selectByPrimaryKey(ids.get(0)).getSchedId();
            Instant instant = new Date().toInstant();
            Instant plus = instant.plus(TICKET_TIME, ChronoUnit.MINUTES);
            // 根据schId查询演出开始时间
            Schedule schedule = scheduleMapper.selectByPrimaryKey(schedId);
            if (plus.compareTo(schedule.getSchedTime().toInstant()) == 1) {
                return false;
            }

            // 如果两张票都ok，或者几张票状态都OK就可以修改状态了，只要几张票中有一张票状态有问题就不能购买
            // 平常也不希望买两张票只成功一张的
            // 修改票的状态
            modifyy(ids, (byte) 9);
            return true;
        }
    }
}
