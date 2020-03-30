package com.demo.parent.userdubboserver.service;

import com.demo.parent.commondubboservice.bean.PageInfo;
import com.demo.parent.commondubboservice.dto.UserDTO;

import java.util.List;

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
     *
     * @param account 用户账号
     * @return UserDTO
     * @author yzh
     * @date 2020/3/18
     */
    UserDTO queryUserByAccount(String account);

    /**
     * 根据角色查询权限信息
     *
     * @param role 角色名
     * @return List<String></>
     * @author yzh
     * @date 2020/3/19
     */
    List<String> queryPermissionsByRole(Integer role);

    PageInfo<UserDTO> queryAllByPage(Integer pageNum, Integer pageSize);

    /**
     * 根据id查询用户信息
     * @param   id
     * @return  UserDTO
     * @author  yzh
     * @date    2020/3/26
     */
    UserDTO queryUserById(Integer id);

    /**
     * 用户信息更新
     * @param user
     * @return int
     * @author  yzh
     * @date    2020/3/26
     */
    int updateUser(UserDTO user);

    /**
     * 保存新用户
     * @param user
     * @return  int
     * @author  yzh
     * @date    2020/3/28
     */
    int saveUser(UserDTO user);
}

