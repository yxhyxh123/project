package com.demo.parent.admin.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * projectName demo
 * className UserVO
 *
 * @author yzh
 * @date 2020/3/18 10:00 上午
 */
@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = 4035058570737491846L;
    private Integer userId;
    private String account;
    private String userName;
    private String password;
    private Integer userRole;
    private Integer userStatus;
    private Boolean rememberMe;


}
