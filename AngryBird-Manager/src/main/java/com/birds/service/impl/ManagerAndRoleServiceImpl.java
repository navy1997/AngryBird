package com.birds.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.birds.mapper.ManagerAndRoleMapper;
import com.birds.mapper.RoleAndPermissionMapper;
import com.birds.pojo.Result;
import com.birds.service.ManagerAndRoleService;
import com.birds.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerAndRoleServiceImpl implements ManagerAndRoleService {

    @Autowired
    private ManagerAndRoleMapper managerAndRoleMapper;
    @Autowired
    private RoleAndPermissionMapper roleAndPermissionMapper;

    @Override
    public Result findManagerRoleByManagerId(Integer id) {
        return Result.success(managerAndRoleMapper.findManagerRoleByManagerId(id));
    }

    @Override
    public Result findPermissionByManagerId(Integer id) {
        List<JSONObject> managerRole = managerAndRoleMapper.findManagerRoleByManagerId(id);
        List<JSONObject> permissionList = new ArrayList<>();
        for (JSONObject i: managerRole) {
            //改用户下所有角色id
            String r_id = String.valueOf(i.get("r_id"));
            //根据角色id获取角色下所有权限
            List<JSONObject> permissionByRoleId = roleAndPermissionMapper.findPermissionByRoleId(Integer.valueOf(r_id));
            for (JSONObject permissionObj: permissionByRoleId) {
                permissionList.add(permissionObj);
            }
        }
        return Result.success(permissionList);
    }
}
