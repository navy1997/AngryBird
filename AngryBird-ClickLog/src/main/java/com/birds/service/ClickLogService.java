package com.birds.service;

import com.birds.pojo.ClickLog;
import com.birds.pojo.Result;
import com.birds.utils.PageRequest;

public interface ClickLogService {

    /**
     * 根据分页器获取点击日志
     * @param pageRequest
     * @return
     */
    public Result findClickLogs(PageRequest pageRequest);


    /**
     * 添加点击日志
     * @param clickLog
     * @return
     */
    public Result insertClickLog(ClickLog clickLog);
}
