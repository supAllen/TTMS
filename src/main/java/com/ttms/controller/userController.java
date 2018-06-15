package com.ttms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ttms.pojo.User;
import com.ttms.service.userService;
import com.ttms.utils.TaotaoResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @Author: Allen
 * @Description:
 * @Date: Created in 20:40 2018/6/13
 * @Modify By:
 */
@Controller
@RequestMapping("/user")
public class userController {

    @Autowired
    public userService userService;
    @Value("${perPageNum}")
    private Integer perPageNum;

    @GetMapping("/list")
    @ResponseBody
    public TaotaoResult selUsers(Integer pn){
        PageHelper.startPage(pn,perPageNum);
        List<User> userList = userService.selectAll();
        PageInfo pageInfo = new PageInfo(userList,perPageNum);
        return TaotaoResult.ok(pageInfo);
    }

    @PostMapping("/add")
    @ResponseBody
    public TaotaoResult addUser(User user){
        userService.addd(user);
        return TaotaoResult.ok();
    }

    @DeleteMapping("/del")
    @ResponseBody
    public TaotaoResult del(String uids){
        userService.del(uids);
        return TaotaoResult.ok();
    }

    @PutMapping("/modify")
    @ResponseBody
    public TaotaoResult updateUserInfo(User user){
        userService.modifyy(user);
        return TaotaoResult.ok();
    }

    @GetMapping("/check")
    @ResponseBody
    public TaotaoResult check(String username,String password) throws UnsupportedEncodingException {
        String s = new String(username.getBytes("iso-8859-1"), "UTF-8");
        boolean b = userService.checkUser(s, password);
        if (b)
            return TaotaoResult.ok();
        else return TaotaoResult.build(500,"登陆失败");
    }

}
