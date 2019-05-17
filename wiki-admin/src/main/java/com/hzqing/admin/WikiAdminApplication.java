package com.hzqing.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hzqing.admin.mapper.*")
public class WikiAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(WikiAdminApplication.class, args);
    }

}
