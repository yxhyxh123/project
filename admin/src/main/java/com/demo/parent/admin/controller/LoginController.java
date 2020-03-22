package com.demo.parent.admin.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;

import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


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
    public String login(String account, String password, Model model) {

        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
        Subject currentUser = SecurityUtils.getSubject();

        try {
            //主体提交登录请求到SecurityManager
            currentUser.login(token);
        } catch (IncorrectCredentialsException ice) {
            model.addAttribute("msg", "密码不正确");
        } catch (UnknownAccountException uae) {
            model.addAttribute("msg", "账号不存在");
        } catch (AuthenticationException ae) {
            model.addAttribute("msg", "状态不正常");
        }
        if (currentUser.isAuthenticated()) {
            System.out.println("认证成功");
            model.addAttribute("account", account);
            return "success";
        } else {
            token.clear();
            return "login";
        }
    }

    /*test*/
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
