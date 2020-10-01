package com.birds.mapper;

import com.birds.pojo.ClickLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ClickLogMapper {
    /**
     * 插入点击日志
     * @param clickLog 点击信息
     * @return
     */
    public Integer insertClickLog(ClickLog clickLog);

    /**
     * 根据时间获取
     * @param map
     * @return
     */
    public List<ClickLog> findClickLogs(Map<String,String> map);


}
