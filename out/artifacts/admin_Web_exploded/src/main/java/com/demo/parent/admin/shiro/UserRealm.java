package com.demo.parent.admin.shiro;

import com.demo.parent.admin.service.LoginService;
import com.demo.parent.admin.util.BeanUtils;
import com.demo.parent.commondubboservice.dto.UserDTO;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
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
    private LoginService loginService;

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
            this.loginService = BeanUtils.getBean(LoginService.class);
        }
        UserDTO user1 = loginService.test();
        UserDTO user = loginService.queryUserByAccount(account);
        if(user == null){
            return null;
        }
        /*做身份验证*/
        SimpleAuthenticationInfo simpleAuthenticationInfo =
                new SimpleAuthenticationInfo(user,user.getPassword(),getName());
        if(StringUtils.isEmpty(user.getUserStatus())){
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
        simpleAuthorizationInfo.addRole(user.getUserRole());
        //添加权限
        simpleAuthorizationInfo.addStringPermissions(user.getPermissions());
        return simpleAuthorizationInfo;
    }
}