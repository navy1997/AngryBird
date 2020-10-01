package com.birds.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private Integer id;
    private Double rate;
    private Integer coverX;
    private String title;
    private String url;
    private Integer playable;   //1：可播放  0：不可播放
    private String cover;
    private String mId;
    private Integer coverY;
    private Integer isNew;   //1:新电影  0：旧电影
    private String tag;
}
