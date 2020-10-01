package com.birds.mapper;

import com.birds.pojo.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ManagerMapper {

    public List<Manager> findManagers(Map<String,String> map);

    public Integer addManager(Manager manager);

    public int findManagerCount(Map<String,String> map);

    public Manager findManagerByUsernameAndPwd(String username,String password);

    public Integer updateManager(Manager manager);

    public Manager findManagerById(Integer id);

    public Integer deleteManagerById(Integer id);


}
