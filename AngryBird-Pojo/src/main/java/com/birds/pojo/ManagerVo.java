package com.birds.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagerVo {
    private Integer id;
    private String username;
    private Integer isDelete;  //1：启用 0：删除
    private String phone;
    private String email;
    private Long createAt;
    private Integer roleId;
    private String token;

    public static ManagerVo getManagerVo(Manager manager){
        //封装vo
        ManagerVo vo = new ManagerVo();
        vo.setId(manager.getId());
        vo.setUsername(manager.getUsername());
        vo.setEmail(manager.getEmail());
        vo.setCreateAt(manager.getCreateAt());
        vo.setIsDelete(manager.getIsDelete());
        vo.setPhone(manager.getPhone());
        vo.setRoleId(manager.getRoleId());
        return vo;
    }
}

