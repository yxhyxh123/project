package com.demo.parent.userdubboserver.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * projectName demo
 * className UserEntity
 *
 * @author yzh
 * @date 2020/3/18 11:21 上午
 */
@Data
@Table(name ="YZH.MEMBER" )
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 7170901961429821794L;

    @Id
    private String id;
    @Column(name = "ACCOUNT")
    private String userAccount;
    @Column(name = "USERNAME")
    private String userName;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "ROLE")
    private String userRole;
    @Column(name = "STATUS")
    private String userStatus;
}
