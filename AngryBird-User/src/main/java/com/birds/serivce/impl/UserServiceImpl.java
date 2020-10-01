package com.birds.serivce.impl;

import com.birds.configs.JwtConfig;
import com.birds.mapper.UserMapper;
import com.birds.pojo.CodeMsg;
import com.birds.pojo.Result;
import com.birds.pojo.User;
import com.birds.serivce.UserService;
import com.birds.utils.JsonUtils;
import com.birds.utils.MD5Utils;
import com.birds.utils.PageRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtConfig jwtConfig;

    @Override
    public Result register(User user) {
        //加密密码
        try{
            user.setPassword(MD5Utils.md5(user.getPassword()));
            userMapper.insertUser(user);
        }catch (Exception e){
            //注册失败了
            e.printStackTrace();
            return Result.error(CodeMsg.DBERROR);
        }
        //注册成功
        user.setPassword(null);
        return Result.success(user);
    }

    @Override
    public Result findUsers(PageRequest pageRequest) {

        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
        List<User> users = userMapper.getUsers();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return Result.success(pageInfo);
    }

    @Override
    public Result login(String username, String password) {
        //加密密码
        User user = userMapper.findUserByUsernameAndPwd(username, MD5Utils.md5(password));
        //判断用户是否存在
        if(StringUtils.isEmpty(user)){
            //用户不存在
            return Result.error(CodeMsg.USERNOTEXISTS);
        }
        //设置token
        try{
            user.setPassword(null);
            String token = jwtConfig.createToken(JsonUtils.objectToJson(user));
            Map<String,Object> m = new HashMap<>();
            m.put("user",user);
            m.put("token",token);
            return Result.success(m);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.error(CodeMsg.USERNOTEXISTS);
    }

    @Override
    public Result findUserById(Integer id) {
        User user = userMapper.findUserById(id);
        return Result.success(user);
    }

    @Override
    public Result findUserByUsername(String username) {
        User user = userMapper.findUserByUsername(username);
        return Result.success(user);
    }

    @Override
    public Result updateUser(User user) {
        Integer row = userMapper.updateUser(user);
        if(row>=1){
            return Result.success(user);
        }
        return Result.error(CodeMsg.USERBASE);
    }

    @Override
    public Result deleteUserById(Integer id) {

        Integer row = userMapper.deleteUserById(id);
        if(row>=1){
            return Result.success(null);
        }

        return Result.error(CodeMsg.USERBASE);
    }
}
