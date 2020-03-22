package com.demo.parent.userdubboserver;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDubbo
@MapperScan(basePackages = {"com.demo.parent.*.dao"})
public class UserDubboServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserDubboServerApplication.class, args);
    }

}
