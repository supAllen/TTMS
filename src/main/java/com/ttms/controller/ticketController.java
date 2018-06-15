package com.ttms.controller;

import com.ttms.service.ticketService;
import com.ttms.serviceInterface.ticketManager;
import com.ttms.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: Allen
 * @Description:
 * @Date: Created in 23:16 2018/6/14
 * @Modify By:
 */
@Controller
@RequestMapping("/ticket")
public class ticketController {

    @Autowired
    private ticketManager ticketService;

    @GetMapping("/info")
    @ResponseBody
    public TaotaoResult getSeatInfo(Integer schId,
                                  @RequestParam(value = "seatIds", defaultValue = "0")
                                          String seatIds){
        boolean b = ticketService.ticketOrder(seatIds, schId);
        if (b)
            return TaotaoResult.ok();
        return TaotaoResult.build(500,"订购失败");
    }

    @GetMapping("/submit")
    @ResponseBody
    public TaotaoResult getSeatInfo(String seatAll){
        System.out.println(seatAll);
        return TaotaoResult.ok();
    }
}
