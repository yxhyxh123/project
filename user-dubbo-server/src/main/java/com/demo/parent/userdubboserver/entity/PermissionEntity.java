package com.demo.parent.userdubboserver.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * projectName demo
 * className PermissionEntity
 *
 * @author yzh
 * @date 2020/3/19 10:05 上午
 */
@Data
@Table(name = "SYSTEM.ROLE_PERMISSION")
public class PermissionEntity {
    @Id
    private Integer id;
    @Column(name = "ROLENAME")
    private String role;
    @Column(name = "PERMISSION")
    private String permission;
}
