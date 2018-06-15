package com.ttms.dao;

import com.ttms.pojo.Play;
import com.ttms.pojo.Schedule;
import com.ttms.pojo.Seat;
import com.ttms.utils.resultType.playPage;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Allen
 * @Description:    数据测试
 * @Date: Created in 11:04 2018/6/4
 * @Modify By:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
public class test {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Autowired
    private SeatMapper seatMapper;

    @Autowired
    private DataDictMapper dataDictMapper;

    @Autowired
    private PlayMapper playMapper;

    @Test
    public void testSch(){
        List<Schedule> schedules = scheduleMapper.selectSchByPlayId(1);
        for (Schedule s : schedules){
            System.out.println(s.getSchedTime());
        }
    }

    @Test
    public void test(){
        Schedule schedule = new Schedule();
        schedule.setSchedTime(new Date());
        schedule.setPlayId(1);
        schedule.setStudioId(1);
        schedule.setSchedTicketPrice(25);
        scheduleMapper.insert(schedule);
    }

    @Test
    public void testaddSeat(){
        List<Seat> seats = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Seat seat = new Seat();
                seat.setSeatStatus((byte) 1);
                seat.setStudioId(13);
                seat.setSeatRow(i);
                seat.setSeatColumn(j);
                seats.add(seat);
            }
        }
        seatMapper.insertBatch(seats);
    }

    @Test
    public void data_dict(){
        int page = 1;
        int startpage = (page-1) * 10;
        System.out.println(dataDictMapper.getPagingData(startpage));
    }

    @Test
    public void test10() throws ParseException {
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.parse("2018-06-14 01:01:00"));
        //Date parse = format.parse("Sat Jun 16 2018 19:04:15 GMT+0800");
        //System.out.println(parse);
        /*Instant instant = Instant.parse("Sat Jun 16 2018 19:04:15 GMT+0800");
        Date date = new Date(instant.getEpochSecond());
        System.out.println(date);*/
        /*Date date1 = DateUtils.parseDate("Sat Jun 16 2018 19:04:15 GMT+0800");
        System.out.println(date1);
        System.out.println(new Date("Sat Jun 16 2018 19:04:15 GMT+0800"));*/
    }

    @Test
    public void testplayPage(){
        playPage playPage = new playPage();
        playPage.setStart(1);
        playPage.setCount(2);
        List<Play> prePageData = playMapper.getPrePageData(playPage);
        System.out.println(prePageData);
    }

    @Test
    public void testInfo(){
        Play playInfo = playMapper.selectByPrimaryKey(3);
        System.out.println(playInfo);
    }

    @Test
    public void seatTest(){
        List<Seat> seatList = seatMapper.getSeatList(9);
        for (Seat s : seatList){
            System.out.println(s.getSeatRow() +" : "+s.getSeatColumn());
        }
    }
}
