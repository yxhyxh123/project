package com.demo.parent.userdubboserver.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * projectName demo
 * className PermissionEntity
 *
 * @author yzh
 * @date 2020/3/19 10:05 上午
 */
@Data
public class PermissionEntity {
    @Id
    private String id;
    @Column(name = "ROLENAME")
    private String role;
    @Column(name = "PERMISSION")
    private String permission;
}