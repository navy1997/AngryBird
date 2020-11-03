package com.birds.mapper;

import com.birds.pojo.FriendsCircle;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/***
 *
 */

@Repository
@Mapper
public interface FriendsCircleMapper {

    public Integer createFriendsCircle(FriendsCircle friendsCircle);

    public List<FriendsCircle> findFriendsCircles(Map<String,String> map);

    public FriendsCircle findFriendsCircleByUid(Integer uId);
}
