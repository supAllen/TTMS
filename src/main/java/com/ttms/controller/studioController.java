package com.ttms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ttms.pojo.Studio;
import com.ttms.service.studioService;
import com.ttms.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: Allen
 * @Description:
 * @Date: Created in 19:50 2018/6/12
 * @Modify By:
 */
@Controller
@RequestMapping("/studio")
public class studioController {

    @Autowired
    private studioService studioService;
    @Value("${perPageNum}")
    private Integer perPageNum;

    @PostMapping("/add")
    @ResponseBody
    public TaotaoResult insertStu(Studio studio){
        studioService.addd(studio);
        return TaotaoResult.ok();
    }

    @GetMapping("/list")
    @ResponseBody
    public TaotaoResult listStu(Integer pn){
        if (pn < 1)
            pn = 1;
        PageHelper.startPage(pn,perPageNum);
        List<Studio> studios = studioService.selectAll();
        PageInfo pageInfo = new PageInfo(studios, perPageNum);
        return TaotaoResult.ok(pageInfo);
    }
}
