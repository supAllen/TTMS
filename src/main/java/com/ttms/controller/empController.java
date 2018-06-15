package com.ttms.controller;

import com.ttms.service.empService;
import com.ttms.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

/**
 * @Author: Allen
 * @Description:
 * @Date: Created in 8:48 2018/6/15
 * @Modify By:
 */
@Controller
@RequestMapping("/root")
public class empController {

    @Autowired
    private empService empService;

    @GetMapping("/check")
    @ResponseBody
    public TaotaoResult check(String username, String password) throws UnsupportedEncodingException {
        boolean b = empService.check(username, password);
        if (b)
            return TaotaoResult.ok();
        else return TaotaoResult.build(500,"登陆失败");
    }
}
