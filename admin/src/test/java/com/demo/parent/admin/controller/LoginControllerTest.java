package com.demo.parent.admin.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.demo.parent.admin.AdminApplication;
import com.demo.parent.admin.service.UserService;
import com.demo.parent.drugdubboserver.service.DrugInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminApplication.class)
public class LoginControllerTest {
    @Autowired
    private UserService loginService;

    @Reference
    private DrugInfoService drugInfoService;


   /* @Test
    public void queryAllByPage() {
        Integer i = drugInfoService.isDrugExist("001","us_394");
        System.out.printf(i.toString());
    }*/
}