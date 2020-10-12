package com.birds.controller;

import com.birds.pojo.ClickLog;
import com.birds.pojo.Result;
import com.birds.service.ClickLogService;
import com.birds.utils.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/clickLog")
public class ClickLogController {


    @Autowired
    private ClickLogService clickLogService;


    @GetMapping("/")
    public Result findClickLogs(@RequestParam(required = false) String query,@RequestParam(defaultValue = "1") Integer pagenum,@RequestParam(defaultValue = "2") Integer pagesize){
        //判断query条件是否传递
        PageRequest pageRequest = new PageRequest();
        if(query != null){
            query = "%" + query + "%";
            pageRequest.getQuery().put("city",query);
        }
        pageRequest.setPageNum(pagenum);
        pageRequest.setPageSize(pagesize);
        Result result = clickLogService.findClickLogs(pageRequest);
        return result;
    }


    @PostMapping("/")
    public Result addClickLog(@RequestBody ClickLog clickLog){
        Result result = clickLogService.insertClickLog(clickLog);
        return result;
    }
}
