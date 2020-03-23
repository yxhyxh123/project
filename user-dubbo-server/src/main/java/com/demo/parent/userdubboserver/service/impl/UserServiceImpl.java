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

import java.util.ArrayList;
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
    public List<String> queryPermissionsByRole(String role) {
        List<String> permissions = new ArrayList<>();
        if(StringUtils.isEmpty(role)){
            return new ArrayList<>();
        }
        PermissionEntity entity = new PermissionEntity();
        entity.setRole(role);
        List<PermissionEntity> lists = permissionMapper.select(entity);
        if(CollectionUtils.isEmpty(lists)){
            return permissions;
        }
         permissions =  lists.stream().
                map(PermissionEntity::getPermission).collect(Collectors.toList());
        return permissions;
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
        if(user != null) {
            BeanUtils.copyProperties(user, userDTO);
            userDTO.setUserId(user.getId());
        }
        return userDTO;
    }
}
