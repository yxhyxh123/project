package com.demo.parent.admin.controller;

import com.demo.parent.admin.bean.Response;
import com.demo.parent.admin.vo.UserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;

import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.demo.parent.admin.enums.ResponseEnum.*;


/**
 * projectName demo
 * className Controller
 *
 * @author yzh
 * @date 2020/3/18 4:44 下午
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public Response<UserVO> login(String account, String password) {
        Response<UserVO> response = new Response<UserVO>();
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            //主体提交登录请求到SecurityManager
            currentUser.login(token);
        } catch (IncorrectCredentialsException ice) {
            response = Response.build(INCORRECT_CREDENTIALS);
        } catch (UnknownAccountException uae) {
            response = Response.build(UNKNOWN_ACCOUNT);
        } catch (AuthenticationException ae) {
            response = Response.build(AUTHENTICATION_EXCETION);
        }
        if (currentUser.isAuthenticated()) {
            System.out.println("认证成功");
            UserVO user = new UserVO();
            String userId = (String)currentUser.getPrincipal();
            user.setUserId(userId);
            user.setUserAccount(account);
            return response;
        } else {
            token.clear();
            response = Response.build(ERROR);
            return response;
        }
    }

    /*test*/
    @ResponseBody
    @GetMapping("/admin")
    public String admin() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("admin")) {
            return "admin";
        } else {
            return "dog×";
        }
    }

    @GetMapping("/test")
    public String test() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isPermitted("test")) {
            return "test";
        } else {
            return "没权限你test个锤子啊!";
        }
    }
}
