package com.birds.service;

import com.birds.pojo.FriendsCircle;
import com.birds.pojo.Result;
import com.birds.utils.PageRequest;

import java.util.List;
import java.util.Map;

public interface FriendsCircleService {

    public Result addFriendsCircle(FriendsCircle friendsCircle);


    public Result findFriendsCircle(PageRequest pageRequest);

    public Result findFriendsCircleByUid(Integer uId);
}
