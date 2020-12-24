package com.bjsxt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bjsxt.mapper")
public class ShiroSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShiroSpringBootApplication.class,args);
    }
}
