package com.birds.mapper;


import com.birds.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface PermissionMapper {

    public List<Permission> findPermission(Map<String,String> map);

    public Permission findPermissionById(Integer id);

    public Integer updatePermission(Permission permission);

}
