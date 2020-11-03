package com.birds.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 朋友圈pojo类
 */
public class FriendsCircle {
    private Integer id;
    private String content;
    private Integer like = 0;
    private Integer uId;
    private String images;
    private Integer isDelete = 1;    //是否删除 1：否 0：是
    private Long createAt = System.currentTimeMillis();
}
