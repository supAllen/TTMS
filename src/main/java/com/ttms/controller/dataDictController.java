package com.ttms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ttms.pojo.DataDict;
import com.ttms.pojo.User;
import com.ttms.service.dictService;
import com.ttms.utils.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Allen
 * @Description:
 * @Date: Created in 20:48 2018/6/13
 * @Modify By:
 */
@Controller
@RequestMapping("/dataDict")
public class dataDictController {

    @Autowired
    private dictService dictService;
    @Value("${perPageNum}")
    private Integer perPageNum;

    @GetMapping("/list")
    @ResponseBody
    public TaotaoResult getDataDictList(Integer pn){
        PageHelper.startPage(pn,perPageNum);
        List<DataDict> userList = dictService.selectAll();
        PageInfo pageInfo = new PageInfo(userList,perPageNum);
        return TaotaoResult.ok(pageInfo);
    }

    @PostMapping("/add")
    @ResponseBody
    public TaotaoResult addDataDict(DataDict dataDict){
        dictService.addd(dataDict);
        return TaotaoResult.ok();
    }

    @DeleteMapping("/del")
    @ResponseBody
    public TaotaoResult delDataDict(String uids){
        dictService.del(uids);
        return TaotaoResult.ok();
    }

    @PutMapping("/modify")
    @ResponseBody
    public TaotaoResult updateDataDict(DataDict dataDict){
        dictService.modifyy(dataDict);
        return TaotaoResult.ok();
    }

    @GetMapping("/info")
    @ResponseBody
    public TaotaoResult DataDictInfo(){
        return TaotaoResult.ok(dictService.getDictTypes());
    }
}
