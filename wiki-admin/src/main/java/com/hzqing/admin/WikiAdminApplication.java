package com.hzqing.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@MapperScan("com.hzqing.admin.mapper")
public class WikiAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(WikiAdminApplication.class, args);
    }


}
