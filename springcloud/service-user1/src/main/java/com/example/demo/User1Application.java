package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: 邵益坤
 * @Date: 2019/8/10 08:47
 * @Description:
 */
@SpringBootApplication

@MapperScan("com.example.demo.mapper")


//@EnableDiscoveryClient

public class User1Application {
    public static void main(String[] args) {
    	    SpringApplication.run(User1Application.class, args); 
    }
}
