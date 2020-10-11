package com.birds.controller;


import com.birds.pojo.Result;
import com.birds.pojo.User;
import com.birds.serivce.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        System.out.println(user);
        Result result = userService.login(user.getUsername(), user.getPassword());
        return result;
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user){
        Result result = userService.register(user);
        return result;
    }
}
