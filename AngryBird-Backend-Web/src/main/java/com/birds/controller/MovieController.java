package com.birds.controller;

import com.birds.pojo.Result;
import com.birds.service.MovieService;
import com.birds.utils.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/")
    public Result getMovies(@RequestParam(required = false) String query, @RequestParam(required = false) String tag,
                            @RequestParam(defaultValue = "1") Integer pagenum, @RequestParam(defaultValue = "10") Integer pagesize,
                            @RequestParam() String order, @RequestParam(defaultValue = "rate") String orderBy){
        PageRequest pageRequest = new PageRequest();
        if(query != null){
            query = "%" + query + "%";
            pageRequest.getQuery().put("title",query);
        }
        if(tag != null && !tag.equals("")){
            pageRequest.getQuery().put("tag",tag);
        }
        if(order != null && !order.equals("")){
            pageRequest.getQuery().put("order",order);
        }
        if(orderBy != null && !orderBy.equals("")){
            pageRequest.getQuery().put("orderBy",orderBy);
        }
        pageRequest.setPageNum(pagenum);
        pageRequest.setPageSize(pagesize);
        return movieService.findMovies(pageRequest);
    }


    @GetMapping("/tags")
    public Result getTags(){
        return movieService.getTags();
    }

    @DeleteMapping("/{id}")
    public Result deleteMovieById(@PathVariable("id") Integer id){
        return movieService.deleteMovieById(id);
    }


}
