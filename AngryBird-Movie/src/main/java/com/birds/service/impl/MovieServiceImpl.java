package com.birds.service.impl;

import com.birds.mapper.MovieMapper;
import com.birds.pojo.*;
import com.birds.service.MovieService;
import com.birds.utils.PageRequest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Override
    public Result findMovies(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Movie> movies = movieMapper.findMovies(pageRequest.getQuery());
        PageInfo<Movie> pageInfo = new PageInfo<>(movies);
        return Result.success(pageInfo);
    }

    @Override
    public Result findMoviesByTag(PageRequest pageRequest) {
        return null;
    }

    @Override
    public Result getTags() {
        List<Tag> tags = movieMapper.getTags();
        return Result.success(tags);
    }

    @Override
    public Result deleteMovieById(Integer id) {

        Integer row = movieMapper.deleteMovieById(id);
        if(row<=0){
            return Result.error(CodeMsg.DBERROR);
        }

        return Result.success(null);
    }
}
