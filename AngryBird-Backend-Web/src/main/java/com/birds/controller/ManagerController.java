package com.birds.controller;

import com.birds.pojo.Manager;
import com.birds.pojo.Result;
import com.birds.service.ManagerService;
import com.birds.utils.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @PostMapping("/login")
    public Object login(@RequestBody Manager manager){
        Result result = managerService.login(manager.getUsername(), manager.getPassword());
        return result;
    }

    @GetMapping("/")
    public Object findManagers(@RequestParam(required = false) String query,@RequestParam(defaultValue = "1") Integer pagenum,@RequestParam(defaultValue = "2") Integer pagesize){
        PageRequest pageRequest = new PageRequest();
        if(query != null){
            query = "%" + query + "%";
            pageRequest.getQuery().put("username",query);
        }
        pageRequest.setPageNum(pagenum);
        pageRequest.setPageSize(pagesize);
        Result result = managerService.findManagers(pageRequest);
        return result;
    }

    @PostMapping("/")
    public Object addManager(@RequestBody Manager manager){
        return managerService.addManager(manager);
    }

    @PutMapping("/{uId}/state/{type}")
    public Object updateManagerState(@PathVariable("uId") Integer id,@PathVariable("type") Integer type){
        Manager manager = new Manager();
        manager.setIsDelete(type);
        manager.setId(id);
        return managerService.updateManager(manager);
    }

    @GetMapping("/{id}")
    public Object getManagerById(@PathVariable("id") Integer id){
        return managerService.findManagerById(id);
    }

    @PutMapping("/{id}")
    public Object updateManager(@PathVariable("id") Integer id,@RequestBody Manager manager){
        manager.setId(id);
        return managerService.updateManager(manager);
    }

    @DeleteMapping("/{id}")
    public Object deleteManagerById(@PathVariable("id") Integer id){
        return managerService.deleteManagerById(id);
    }
}
