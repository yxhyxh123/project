package com.demo.parent.userdubboserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.demo.parent.commondubboservice.dto.UserDTO;
import com.demo.parent.userdubboserver.dao.PermissionMapper;
import com.demo.parent.userdubboserver.dao.UserMapper;
import com.demo.parent.userdubboserver.entity.PermissionEntity;
import com.demo.parent.userdubboserver.entity.UserEntity;
import com.demo.parent.userdubboserver.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * projectName demo
 * className UserServiceImpl
 *
 * @author yzh
 * @date 2020/3/18 5:16 下午
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public UserDTO queryPermissionsByRole(String role) {
        UserDTO userDTO = new UserDTO();
        if(StringUtils.isEmpty(role)){
            return userDTO;
        }
        PermissionEntity entity = new PermissionEntity();
        entity.setRole(role);
        List<PermissionEntity> lists = permissionMapper.select(entity);
        if(CollectionUtils.isEmpty(lists)){
            return userDTO;
        }
        List<String> permissions =  lists.stream().
                map(PermissionEntity::getPermission).collect(Collectors.toList());
        userDTO.setPermissions(permissions);
        return userDTO;
    }

    @Override
    public UserDTO queryUserByAccount(String account) {
        UserDTO userDTO  =  new UserDTO();
        if(StringUtils.isEmpty(account)){
            return userDTO;
        }
        UserEntity user =  new UserEntity();
        user.setUserAccount(account);
        user = userMapper.selectOne(user);
        BeanUtils.copyProperties(user,userDTO);
        return userDTO;
    }
}
