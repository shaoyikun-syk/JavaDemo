package com.example.demo.controller;

import com.example.demo.bean.User2;
import com.example.demo.service.User2Service;
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
@RequestMapping("user2")
public class User2Controller {

    @Autowired
    private User2Service user2Service;

    @GetMapping
    public Result findAll(){

        List<User2> user2List = user2Service.findAll();
        System.out.println("user2服务调用.............");
        return new Result(true,200,"查询成功", user2List);
    }
}
