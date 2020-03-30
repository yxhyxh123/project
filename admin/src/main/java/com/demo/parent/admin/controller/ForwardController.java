package com.demo.parent.admin.controller;

import com.demo.parent.admin.bean.Response;
import com.demo.parent.admin.service.UserService;
import com.demo.parent.admin.vo.UserEditVO;
import com.demo.parent.admin.vo.UserVO;
import com.demo.parent.commondubboservice.bean.PageInfo;
import com.demo.parent.commondubboservice.dto.UserDTO;
import org.apache.shiro.SecurityUtils;

import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * projectName demo
 * className Controller
 *
 * @author yzh
 * @date 2020/3/18 4:44 下午
 */
@Controller
public class ForwardController {

    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public String login() {
        return "loginH";
    }


    @GetMapping("/page")
    public String page(){
        Subject subject= SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            return "admin";
        }
        return "loginH";
    }

    @GetMapping("/userManagement")
    public String userManagement(){
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("admin")){
            return "userManagement";
        }
        return "loginFirst";
    }

    @GetMapping("/drugManagement")
    public String drugManagement(){
        Subject  subject  = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            return "drugManagement";
        }
        return "loginFirst";
    }

    @GetMapping("/stock")
    public String admin() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("admin")) {
            return "stock";
        } else {
            return "loginH";
        }
    }

}
