package com.demo.parent.admin.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * projectName demo
 * className UserAddVo
 * description TODO
 *
 * @author yzh
 * @date 2020/3/29 6:19 下午
 */
@Data
public class UserAddVO implements Serializable {

    private static final long serialVersionUID = 3866783220322869155L;
    private Integer id;
    private String userAccount;
    private String password;
    private String userNameAdd;
    private Integer userRole;
    private Integer userStatus;
}
