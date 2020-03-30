package com.demo.parent.admin.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.demo.parent.admin.bean.Response;

import com.demo.parent.admin.service.UserService;
import com.demo.parent.admin.vo.UserAddVO;
import com.demo.parent.admin.vo.UserEditVO;
import com.demo.parent.admin.vo.UserVO;
import com.demo.parent.commondubboservice.bean.PageInfo;
import com.demo.parent.commondubboservice.dto.UserDTO;
import com.demo.parent.commondubboservice.util.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;


import static com.demo.parent.admin.enums.ResponseEnum.*;

/**
 * projectName demo
 * className LoginServiceImpl
 * description TODO
 *
 * @author yzh
 * @date 2020/3/18 4:50 下午
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Reference
    private com.demo.parent.userdubboserver.service.UserService userService;


    @Override
    public UserDTO queryUserByAccount(String account) {
        if(StringUtils.isEmpty(account)){
            return new UserDTO();
        }
        UserDTO userDTO = userService.queryUserByAccount(account);
        List<String> permissions = userService.queryPermissionsByRole(userDTO.getUserRole());
        userDTO.setPermissions(permissions);
        return userDTO;
    }

    @Override
    public Response<UserVO> login(UserVO user) {
        Response<UserVO> response = new Response<UserVO>();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(),MD5Util.getMD5String(user.getPassword()));
        Subject currentUser = SecurityUtils.getSubject();
        try {
           // token.setRememberMe(user.getRememberMe());
            //主体提交登录请求到SecurityManager
            currentUser.login(token);
        } catch (IncorrectCredentialsException ice) {
            return Response.build(INCORRECT_CREDENTIALS);
        } catch (UnknownAccountException uae) {
            return Response.build(UNKNOWN_ACCOUNT);
        } catch (AuthenticationException ae) {
            return Response.build(AUTHENTICATION_EXCEPTION);
        }
        if (currentUser.isAuthenticated()) {
            logger.info(AUTHENTICATION_SUCCESS.getMsg());
            UserDTO dto = (UserDTO)currentUser.getPrincipal();
            BeanUtils.copyProperties(dto,user);
            user.setAccount(dto.getUserAccount());
            response.setData(user);
            response =Response.build(SUCCESS);
            return response;
        } else {
            token.clear();
            response = Response.build(ERROR);
            return response;
        }
    }

   /* @Override
    public Response<PageInfo> queryAllByPage(Integer pageNum, Integer pageSize) {
        PageInfo<UserDTO> pages  = userService.queryAllByPage(pageNum,pageSize);
        Response<PageInfo> response = Response.build(SUCCESS);
        response.setData(pages);
        return response;
    }*/

    @Override
    public PageInfo<UserDTO> queryAllByPage(Integer pageNum, Integer pageSize) {
        PageInfo<UserDTO> pages  = userService.queryAllByPage(pageNum,pageSize);
        return pages;
    }

    @Override
    public Response<UserEditVO> queryUserById(Integer id) {
        if (null == id){
            return Response.build(PARAMS_IS_EMPTY);
        }
        UserDTO dto = userService.queryUserById(id);
        UserEditVO vo = new UserEditVO();
        BeanUtils.copyProperties(dto,vo);
        return Response.build(SUCCESS,vo);
    }


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Response updateUser(UserEditVO vo) {
        UserDTO dto =  new UserDTO();
        BeanUtils.copyProperties(vo,dto);
        userService.updateUser(dto);
        return Response.success();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Response addUser(UserAddVO vo) {
        UserDTO dto =  new UserDTO();
        UserDTO users = userService.queryUserByAccount(vo.getUserAccount());
        if(users.getUserAccount() != null ){
            return Response.build(ACCOUNT_IS_EXIST);
        }
        BeanUtils.copyProperties(vo,dto);
        dto.setUserName(vo.getUserNameAdd());
        String pwd = vo.getPassword();
        dto.setPassword(MD5Util.getMD5String(pwd));
        userService.saveUser(dto);
        return Response.success();    }

    private void buildUserEditVO(UserDTO dto, UserEditVO vo){
        BeanUtils.copyProperties(dto,vo);
        vo.setId(dto.getId());
    }
}
