package com.demo.parent.admin.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * projectName demo
 * className UserEditVO
 *
 * @author yzh
 * @date 2020/3/26 8:03 上午
 */
@Data
public class UserEditVO implements Serializable {

    private static final long serialVersionUID = 9183349590847830724L;

    private Integer id;
    private String userAccount;
    private String password;
    private String userName;
    private Integer userRole;
    private Integer userStatus;
}
