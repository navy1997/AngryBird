package com.birds.controller;


import com.birds.pojo.Result;
import com.birds.service.FriendsCircleService;
import com.birds.utils.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/friendsCircle")
public class FriendsCircleController {

    @Autowired
    private FriendsCircleService friendsCircleService;

    @GetMapping("/")
    public Result getFriendsCircles(@RequestParam(defaultValue = "1") Integer pagenum,@RequestParam(defaultValue = "10") Integer pagesize){
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageNum(pagenum);
        pageRequest.setPageSize(pagesize);
        return friendsCircleService.findFriendsCircle(pageRequest);
    }
}
