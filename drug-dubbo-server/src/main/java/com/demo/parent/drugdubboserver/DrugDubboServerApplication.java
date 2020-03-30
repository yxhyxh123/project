package com.demo.parent.drugdubboserver;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDubbo
@MapperScan(basePackages = {"com.demo.parent.*.dao"})
public class DrugDubboServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DrugDubboServerApplication.class, args);
    }

}
