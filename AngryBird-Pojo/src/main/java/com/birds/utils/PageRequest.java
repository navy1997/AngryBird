package com.birds.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 分页请求
 */
public class PageRequest {
    /**
     * 当前页码
     */
    private int pageNum;
    /**
     * 每页数量
     */
    private int pageSize;

    private Map<String,String> query = new HashMap<>();

    public int getPageNum() {
        return pageNum;
    }
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Map<String,String> getQuery() {
        return query;
    }
    public void setQuery(Map<String,String> query) {
        this.query = query;
    }

}