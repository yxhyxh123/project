package com.demo.parent.userdubboserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.demo.parent.commondubboservice.bean.PageInfo;
import com.demo.parent.commondubboservice.dto.UserDTO;
import com.demo.parent.commondubboservice.enums.RoleEnum;
import com.demo.parent.commondubboservice.util.BeanUtil;
import com.demo.parent.commondubboservice.util.PageUtil;
import com.demo.parent.userdubboserver.dao.PermissionMapper;
import com.demo.parent.userdubboserver.dao.UserMapper;
import com.demo.parent.userdubboserver.entity.PermissionEntity;
import com.demo.parent.userdubboserver.entity.UserEntity;
import com.demo.parent.userdubboserver.service.UserService;
import com.github.pagehelper.PageHelper;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
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
    public List<String> queryPermissionsByRole(Integer role) {
        List<String> permissions = new ArrayList<>();
        if(StringUtils.isEmpty(role)){
            return new ArrayList<>();
        }
        PermissionEntity entity = new PermissionEntity();
        entity.setRole(RoleEnum.getName(role));
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
            userDTO.setId(user.getId().intValue());
        }
        return userDTO;
    }
    @Override
    public PageInfo<UserDTO> queryAllByPage(Integer pageNum, Integer pageSize) {
        if(null == pageNum){
            pageNum = 1;
        }
        if(null == pageSize){
            pageSize = 10;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<UserEntity> users = userMapper.selectAll();
        List<UserDTO> dtos = new ArrayList<>();
        if(!CollectionUtils.isEmpty(users)){
            dtos = BeanUtil.copyListProperties(UserDTO.class,users);
        }
        dtos.stream().forEach(p->{
            if(p.getCreateTime() != null){
                Date date = new Date(p.getCreateTime());
                p.setCreateDate(date);
            }
            if(p.getModifyTime() != null){
                Date date = new Date(p.getModifyTime());
                p.setModifyDate(date);
            }
        });
        PageInfo<UserDTO> page = new PageInfo<UserDTO>();
        int total = userMapper.queryAllCount();
        PageUtil.buildListPage(pageNum,pageSize,dtos,page,total);
        return page;
    }

    @Override
    public UserDTO queryUserById(Integer id) {
        UserDTO user = new UserDTO();
        if (null == id){
            return user;
        }
        UserEntity en = new UserEntity();
        en.setId(id);
        en = userMapper.selectOne(en);
        BeanUtils.copyProperties(en,user);
        return user;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int updateUser(UserDTO user) {
        if(null ==  user){
            return 0;
        }
        UserEntity entity  = new UserEntity();
        BeanUtils.copyProperties(user,entity);
        Long modifyTime = System.currentTimeMillis();
        entity.setModifyTime(modifyTime);
        Example example= new Example(UserEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",entity.getId());
      return userMapper.updateByExampleSelective(entity,example);
    }

    @Override
    public int saveUser(UserDTO user) {
        if(null == user){
            return 0;
        }
        UserEntity entity  = new UserEntity();
        BeanUtils.copyProperties(user,entity);
        Long createTime = System.currentTimeMillis();
        entity.setCreateTime(createTime);
        return userMapper.insertSelective(entity);
    }
}
