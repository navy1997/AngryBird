package com.birds.service.impl;

import com.birds.mapper.ClickLogMapper;
import com.birds.pojo.ClickLog;
import com.birds.pojo.CodeMsg;
import com.birds.pojo.Result;
import com.birds.service.ClickLogService;
import com.birds.utils.PageRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClickLogServiceImpl implements ClickLogService {

    @Autowired
    private ClickLogMapper clickLogMapper;

    @Override
    public Result findClickLogs(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
        List<ClickLog> logs = clickLogMapper.findClickLogs(pageRequest.getQuery());
        PageInfo<ClickLog> pageInfo = new PageInfo<>(logs);
        return Result.success(pageInfo);
    }

    @Override
    public Result insertClickLog(ClickLog clickLog) {
        Integer row = clickLogMapper.insertClickLog(clickLog);
        if(row>0){
            return Result.success(clickLog);
        }
        return Result.error(CodeMsg.DBERROR);
    }
}
