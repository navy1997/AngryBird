package com.birds.mapper;


import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ManagerAndRoleMapper {

    List<JSONObject> findManagerRoleByManagerId(Integer id);

}
