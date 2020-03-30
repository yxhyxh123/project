package com.demo.parent.admin.controller;

import com.demo.parent.admin.bean.Response;
import com.demo.parent.admin.enums.ResponseEnum;
import com.demo.parent.admin.service.UserService;
import com.demo.parent.admin.vo.UserAddVO;
import com.demo.parent.admin.vo.UserEditVO;
import com.demo.parent.admin.vo.UserVO;
import com.demo.parent.commondubboservice.bean.PageInfo;
import com.demo.parent.commondubboservice.dto.UserDTO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * projectName demo
 * className UserController
 * description TODO
 *
 * @author yzh
 * @date 2020/3/27 2:56 下午
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Response login(UserVO user) {
        return userService.login(user);
    }

    @RequestMapping(value="/queryAllByPage")
    public PageInfo<UserDTO> queryAllByPage(
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize
    ){
        PageInfo<UserDTO> pages  = userService.queryAllByPage(pageNum,pageSize);
        return pages;
    }

    @GetMapping("/queryUserById")
    public Response<UserEditVO> queryUserById(
            @RequestParam(value = "id")Integer id
    ){
        return userService.queryUserById(id);
    }

    @PostMapping(value="/updateUser")
    public Response updateUser(@RequestBody UserEditVO user){
        return userService.updateUser(user);
    }

    @PostMapping("/addUser")
    public Response addUser(@RequestBody UserAddVO user){
        return userService.addUser(user);
    }

    @GetMapping("/queryUserRole")
    public Response queryUserRole(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("admin")){
            return Response.build(200,"admin");
        }else if (subject.hasRole("user")){
            return Response.build(300,"user");
        }
        return Response.build(ResponseEnum.ERROR);
    }
}
