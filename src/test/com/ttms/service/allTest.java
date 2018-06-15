package com.ttms.service;

import com.ttms.pojo.*;
import com.ttms.utils.resultType.playInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author: Allen
 * @Description:
 * @Date: Created in 8:52 2018/6/6
 * @Modify By:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"
        ,"classpath:spring/spring-service.xml","classpath:spring/spring-redis.xml"})
public class allTest {

    @Autowired
    playService ps;

    @Autowired
    scheduleService ss;

    @Autowired
    studioService sss;

    @Autowired
    dictService ds;

    @Autowired
    ticketService ts;

    @Autowired
    seatService seatService;

    @Test
    public void test1(){
        /*List<List<String>> playTypesAndLanguages = ps.getPlayTypesAndLanguages();
        System.out.println(playTypesAndLanguages);*/
    }

    @Test
    public void test2(){
        List<Play> playList = ps.selectByCondition("动画片,国语");
        playList.forEach(System.out::println);
    }

    @Test
    public void test3(){
        Schedule schedule = new Schedule();
        schedule.setSchedId(2);
        schedule.setStudioId(1);
        schedule.setPlayId(1);
        schedule.setSchedTime(new Date());
        schedule.setSchedTicketPrice(50);
        //ss.modifySch(schedule);
    }

    @Test
    public void test4(){
        Studio studio = new Studio();
        studio.setStudioId(1);
        studio.setStudioName("1号演播厅");
        studio.setStudioColCount(10);
        studio.setStudioRowCount(10);
        studio.setStudioIntroduction("xiugail");
        studio.setStudioFlag((byte) 1);
        sss.modifyy(studio);
    }

    @Test
    public void test5(){
        sss.selectAll().forEach(System.out::print);
    }

    @Test
    public void test6(){
        List<List<String>> dictTypes = ds.getDictTypes();
        for (List<String> ld : dictTypes){
            for (String dd : ld){
                System.out.println(dd);
            }
        }
    }

    @Test
    public void test7(){
        Map<Integer, List<Seat>> seatAndSch = ts.getSeatAndSch();
        for (Map.Entry<Integer,List<Seat>> map : seatAndSch.entrySet()){
            System.out.println("这里是第个"+map.getKey()+"演出计划");
            for (Seat s : map.getValue()){
                System.out.println("我将坐在第"+s.getSeatRow()+
                        "行"+s.getSeatColumn()+"列");
            }
        }
    }

    @Test
    public void tset8(){
        /*ts.batchAddTicket(9);*/
    }

    @Test
    public void test9(){
        seatService.modifySeatStatus("12104,12105");
    }

    @Test
    public void test10(){
        List<Schedule> schedules = ss.selectByPlayIdGetNowDayData(1);
        for (Schedule schedule:schedules){
            System.out.println(schedule.getSchedTime());
        }
    }

    @Test
    public void playInfo(){
        playInfo prePlayInfo = ps.getPrePlayInfo(1);
        System.out.println(prePlayInfo);
    }
}
