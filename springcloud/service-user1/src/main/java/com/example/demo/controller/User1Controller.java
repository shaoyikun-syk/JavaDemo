package com.example.demo.controller;

import com.example.demo.bean.User1;
import com.example.demo.service.User1Service;
import com.example.demo.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 邵益坤
 * @Date: 2019/8/10 08:40
 * @Description:
 */
@RestController
@RequestMapping("user1")
public class User1Controller {

    @Autowired
    private User1Service user1Service;

    @GetMapping
    public Result findAll(){

        List<User1> user1List=user1Service.findAll();
        System.out.println("user1服务调用.............");
        return new Result(true,200,"查询成功",user1List);
    }
}
