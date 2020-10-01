package com.birds.controller;


import com.birds.pojo.Result;
import com.birds.pojo.User;
import com.birds.serivce.UserService;
import com.birds.utils.PageRequest;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public Result getUsers(@RequestParam(defaultValue = "1") Integer pagenum,@RequestParam(defaultValue = "10") Integer pagesize){
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageNum(pagenum);
        pageRequest.setPageSize(pagesize);
        return userService.findUsers(pageRequest);
    }

    @GetMapping("/{id}")
    public Result getUserById(@PathVariable("id") Integer id){
        Result user = userService.findUserById(id);
        return Result.success(user);
    }

    @DeleteMapping("/{id}")
    public Result deleteUserById(@PathVariable("id") Integer id){
        User u = new User();
        u.setId(id);
        u.setIsDelete(0);
        Result result = userService.updateUser(u);
        return result;
    }

    @PutMapping("/{id}")
    public Result updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @GetMapping("/name/{name}")
    public Result getUserByUsername(@PathVariable("name") String username){
        return userService.findUserByUsername(username);
    }
}
