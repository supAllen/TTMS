package com.ttms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ttms.pojo.Schedule;
import com.ttms.service.scheduleService;
import com.ttms.utils.TaotaoResult;
import com.ttms.utils.dateFormatUtils;
import com.ttms.utils.resultType.schInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @Author: Allen
 * @Description:
 * @Date: Created in 23:17 2018/6/12
 * @Modify By:
 */
@Controller
@RequestMapping("/schedule")
public class schduleController {

    @Autowired
    private scheduleService scheduleService;
    @Value("${perPageNum}")
    private Integer perPageNum;

    @GetMapping("/list")
    @ResponseBody
    public TaotaoResult listStu(Integer pn){
        PageHelper.startPage(pn,perPageNum);
        List<Schedule> schedules = scheduleService.selectAll();
        PageInfo pageInfo = new PageInfo(schedules,perPageNum);
        return TaotaoResult.ok(pageInfo);
    }

    @PostMapping("/add")
    @ResponseBody
    public TaotaoResult insertStu(HttpServletRequest request) throws ParseException {
        Schedule schedule = new Schedule();
        schedule.setStudioId(Integer.valueOf(request.getParameter("studioId")));
        schedule.setPlayId(Integer.valueOf(request.getParameter("playId")));
        Date date = dateFormatUtils.getDate(request.getParameter("schedTime"));
        schedule.setSchedTime(date);
        schedule.setSchedTicketPrice(Integer.valueOf(request.getParameter("schedTicketPrice")));
        return scheduleService.addSch(schedule);
    }

    @GetMapping("/info")
    @ResponseBody
    public schInfo schInfoGet(){
        return scheduleService.getschInfo();
    }

    @GetMapping("/selplayTicketPrice")
    @ResponseBody
    public TaotaoResult getplayTicketPrice(Integer pid){
        Integer playTicketPrice = scheduleService.getPlayTicketPrice(pid);
        return TaotaoResult.ok(playTicketPrice);
    }

    @PostMapping("/del")
    @ResponseBody
    public TaotaoResult schDel(Integer schedId){
        System.out.println(schedId);
        scheduleService.del(schedId+"");
        return TaotaoResult.ok();
    }

    @PostMapping("/modify")
    @ResponseBody
    public TaotaoResult schModify(HttpServletRequest request) throws ParseException {
        Schedule schedule = new Schedule();
        schedule.setSchedId(Integer.valueOf(request.getParameter("schedId")));
        schedule.setStudioId(Integer.valueOf(request.getParameter("studioId")));
        schedule.setPlayId(Integer.valueOf(request.getParameter("playId")));
        Date date = dateFormatUtils.getDate(request.getParameter("schedTime"));
        schedule.setSchedTime(date);
        schedule.setSchedTicketPrice(Integer.valueOf(request.getParameter("schedTicketPrice")));
        scheduleService.modifyy(schedule);
        return TaotaoResult.ok();
    }

    @GetMapping("/content")
    @ResponseBody
    public TaotaoResult getContent(Integer schedId){
        return TaotaoResult.ok(scheduleService.getSch(schedId));
    }
}
