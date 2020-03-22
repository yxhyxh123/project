package com.demo.parent.userdubboserver.service;

import com.demo.parent.commondubboservice.dto.UserDTO;

/**
 * projectName demo
 * interfaceName UserService
 * description 用户服务接口
 *
 * @author yzh
 * @date 2020/3/18 5:11 下午
 */
public interface UserService {

    /**
     * 查询用户账号查询用户
     * @param  account 用户账号
     * @return  UserDTO
     * @author  yzh
     * @date    2020/3/18
     */
    UserDTO queryUserByAccount(String account);

    /**
     * 根据角色查询权限信息
     * @param  role 角色名
     * @return  dto
     * @author  yzh
     * @date    2020/3/19
     */
    UserDTO queryPermissionsByRole(String role);
}
