package com.birds.service;


import com.birds.pojo.Result;
import com.birds.utils.PageRequest;

public interface MovieService {

    public Result findMovies(PageRequest pageRequest);

    public Result findMoviesByTag(PageRequest pageRequest);

    public Result getTags();

    public Result deleteMovieById(Integer id);
}
