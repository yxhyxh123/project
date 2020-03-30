package com.demo.parent.commondubboservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
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

    private Integer id;
    private String userAccount;
    private String userName;
    private String password;
    private Integer userRole;
    private Integer userStatus;
    private Long createTime;
    private Long modifyTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date modifyDate;
    private List<String> permissions;
}
