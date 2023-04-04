package com.breeze.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScan(basePackages ={"com.breeze"})
@MapperScan("com.breeze.dao")
public class BreezeAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BreezeAdminApplication.class, args);
    }

}
