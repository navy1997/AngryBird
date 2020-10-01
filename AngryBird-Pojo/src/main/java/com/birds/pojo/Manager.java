package com.birds.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manager {
    private Integer id;
    private String username;
    private String password;
    private Integer isDelete = 1;  //1：启用 0：删除
    private String phone;
    private String email;
    private Long createAt = System.currentTimeMillis();
    private Integer roleId;
}
