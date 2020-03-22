package com.demo.parent.commondubboservice.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * projectName demo
 * className UserDTO
 *
 * @author yzh
 * @date 2020/3/18 5:02 下午
 */
@Data
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 421537692859618575L;

    private String userId;
    private String userAccount;
    private String userName;
    private String password;
    private String userRole;
    private String userStatus;
    private List<String> permissions;
}
