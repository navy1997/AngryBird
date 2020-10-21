package com.birds.mapper;

import com.birds.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface RoleMapper {

    public List<Role> findRoles(Map<String,String> map);

    public Integer addRole(Role role);

    public Role findRoleById(Integer id);

    public Integer updateRole(Role role);
}
