package com.birds.mapper;


import com.birds.pojo.Movie;
import com.birds.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface MovieMapper {

    public List<Movie> findMovies(Map<String,String> map);

    public Integer findMovieCount(Map<String,String> map);

    public List<Movie> findMoviesByTag(String tag);

    public List<Tag> getTags();

    public Integer deleteMovieById(Integer id);

}
