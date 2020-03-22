package com.demo.parent.admin.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.demo.parent.admin.service.LoginService;
import com.demo.parent.admin.util.BeanUtils;
import com.demo.parent.commondubboservice.dto.UserDTO;
import com.demo.parent.userdubboserver.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * projectName demo
 * className LoginServiceImpl
 * description TODO
 *
 * @author yzh
 * @date 2020/3/18 4:50 下午
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Reference
    private UserService userService;


    @Override
    public UserDTO queryUserByAccount(String account) {
        if(StringUtils.isEmpty(account)){
            return new UserDTO();
        }
        if(userService == null){
            this.userService = BeanUtils.getApplicationContext().getBean(UserService.class);
        }
        return userService.queryUserByAccount(account);
    }

    @Override
    public UserDTO test() {
        UserDTO user = new UserDTO();
        user.setUserId("123");
        return user;
    }
}
