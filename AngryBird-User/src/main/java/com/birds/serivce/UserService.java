package com.birds.serivce;

import com.birds.pojo.Result;
import com.birds.pojo.User;
import com.birds.utils.PageRequest;

public interface UserService {

    /**
     * 注册用户
     * @param user 用户对象
     * @return
     */
    public Result register(User user);

    /**
     * 获取所有用户
     * @return
     */
    public Result findUsers(PageRequest pageRequest);

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public Result login(String username,String password);

    /**
     * 根据id获取用户
     * @param id 用户id
     * @return
     */
    public Result findUserById(Integer id);

    /**
     * 根据用户名精确获取用户
     * @param username 用户名
     * @return
     */
    public Result findUserByUsername(String username);


    /**
     * 更新用户
     * @param user 用户信息
     * @return
     */
    public Result updateUser(User user);

    /**
     * 根据id物理删除用户
     * @param id 用户id
     * @return
     */
    public Result deleteUserById(Integer id);
}
