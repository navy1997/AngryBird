package com.birds.mapper;


import com.birds.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户mapper
 */
@Repository
@Mapper
public interface UserMapper {
    public User findUserById(Integer id);

    public List<User> getUsers();

    public User findUserByUsernameAndPwd(String username,String password);

    public User findUserByUsername(String username);

    public Integer deleteUserById(Integer id);

    public Integer updateUser(User user);

    public Integer insertUser(User user);
}
