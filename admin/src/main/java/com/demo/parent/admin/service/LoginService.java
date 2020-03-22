package com.demo.parent.admin.service;

import com.demo.parent.commondubboservice.dto.UserDTO;

/**
 * projectName demo
 * interfaceName LoginService
 *
 * @author yzh
 * @date 2020/3/18 4:50 下午
 */
public interface LoginService {

    /**
     * @author  yzh
     * @date    2020/3/19
     */
    UserDTO queryUserByAccount(String account);

    UserDTO test();
}
