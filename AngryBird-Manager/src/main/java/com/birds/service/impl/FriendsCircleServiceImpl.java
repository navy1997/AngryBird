package com.birds.service.impl;

import com.birds.mapper.FriendsCircleMapper;
import com.birds.pojo.CodeMsg;
import com.birds.pojo.FriendsCircle;
import com.birds.pojo.Manager;
import com.birds.pojo.Result;
import com.birds.service.FriendsCircleService;
import com.birds.utils.PageRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FriendsCircleServiceImpl implements FriendsCircleService {

    @Autowired
    private FriendsCircleMapper friendsCircleMapper;

    @Override
    public Result addFriendsCircle(FriendsCircle friendsCircle) {

        Integer row = friendsCircleMapper.createFriendsCircle(friendsCircle);
        if(row>0){
            return Result.success(friendsCircle);
        }
        return Result.error(CodeMsg.DBERROR);
    }

    @Override
    public Result findFriendsCircle(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<FriendsCircle> friendsCircles = friendsCircleMapper.findFriendsCircles(pageRequest.getQuery());
        PageInfo<FriendsCircle> pageInfo = new PageInfo<>(friendsCircles);
        return Result.success(pageInfo);
    }

    @Override
    public Result findFriendsCircleByUid(Integer uId) {
        FriendsCircle friendsCircleByUid = friendsCircleMapper.findFriendsCircleByUid(uId);
        return Result.success(friendsCircleByUid);
    }
}
