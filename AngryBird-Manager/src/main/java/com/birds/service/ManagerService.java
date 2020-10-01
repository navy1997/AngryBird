package com.birds.service;

import com.birds.pojo.Manager;
import com.birds.pojo.Result;
import com.birds.utils.PageRequest;

public interface ManagerService {

    public Result login(String username, String password);

    public Result findManagers(PageRequest pageRequest);

    public Result addManager(Manager manager);

    public Result updateManager(Manager manager);

    public Result findManagerById(Integer id);

    public Result deleteManagerById(Integer id);
}
