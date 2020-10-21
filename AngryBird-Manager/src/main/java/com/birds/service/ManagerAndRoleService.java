package com.birds.service;

import com.birds.pojo.Result;

public interface ManagerAndRoleService {

    public Result findManagerRoleByManagerId(Integer id);

    public Result findPermissionByManagerId(Integer id);
}
