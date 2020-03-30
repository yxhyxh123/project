package com.demo.parent.admin.shiro;

import com.demo.parent.admin.service.UserService;
import com.demo.parent.admin.util.BeanUtils;
import com.demo.parent.commondubboservice.dto.UserDTO;
import com.demo.parent.commondubboservice.enums.RoleEnum;
import com.demo.parent.commondubboservice.util.MD5Util;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * projectName demo
 * className UserRealm
 *自定义的realm实现业务逻辑
 * @author yzh
 * @date 2020/3/18 4:55 下午
 */
public class UserRealm extends AuthorizingRealm {

    private static final String ACCOUNT_IS_FORBIDDEN = "账号被禁用";


    @Autowired
    private UserService loginService;

    /**
     * 认证
     * @author  yzh
     * @date    2020/3/18
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1.获取用户输入的账号
        String account = (String)token.getPrincipal();
        if(loginService == null){
            this.loginService = BeanUtils.getBean(UserService.class);
        }
        UserDTO user = loginService.queryUserByAccount(account);
        if(StringUtils.isEmpty(user.getId())){
            return null;
        }
        /*做身份验证*/
        SimpleAuthenticationInfo simpleAuthenticationInfo =
                new SimpleAuthenticationInfo(user,user.getPassword(),getName());
        if(user.getUserStatus() == 0){
            throw new AuthenticationException(ACCOUNT_IS_FORBIDDEN);
        }
        return simpleAuthenticationInfo;
    }

    /**
     * @description 授权
     * 添加当前用户拥有的角色和权限，与主体的授权信息进行比对。
     * @author  yzh
     * @date    2020/3/19
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        //获取当前登录的用户
        UserDTO user = (UserDTO) principal.getPrimaryPrincipal();
        //通过SimpleAuthenticationInfo做授权
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //添加角色
        Integer role = user.getUserRole();
        simpleAuthorizationInfo.addRole(RoleEnum.getName(role));
        //添加权限
        if(!CollectionUtils.isEmpty(user.getPermissions())) {
            simpleAuthorizationInfo.addStringPermissions(user.getPermissions());
        }
        return simpleAuthorizationInfo;
    }
}
