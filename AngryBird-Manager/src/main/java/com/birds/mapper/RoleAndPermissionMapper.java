package com.birds.mapper;


import com.alibaba.fastjson.JSONObject;
import com.birds.pojo.RoleAndPermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleAndPermissionMapper {
    public List<JSONObject> findPermissionByRoleId(Integer id);
}
