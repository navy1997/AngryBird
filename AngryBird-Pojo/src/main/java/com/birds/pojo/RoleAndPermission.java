package com.birds.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleAndPermission {

    private Integer id;
    private Integer roleId;
    private Integer permissionId;

}
