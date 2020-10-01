package com.birds.pojo;

import java.math.BigInteger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer isDelete;
    private String phone;
    private String email;
    private Long createAt;
    private Long updateAt;
    private Long loginTime;
    private String loginIp;
}
