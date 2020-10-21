package com.birds.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagerAndRole {

    private Integer id;
    private Integer userId;
    private Integer roleId;
}
