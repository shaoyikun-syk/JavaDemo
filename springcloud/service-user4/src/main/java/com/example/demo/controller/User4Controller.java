package com.example.demo.controller;

import com.example.demo.bean.User4;
import com.example.demo.service.User4Service;
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
@RequestMapping("user4")
public class User4Controller {

    @Autowired
    private User4Service user4Service;

    @GetMapping
    public Result findAll(){

        List<User4> user4List = user4Service.findAll();
        System.out.println("user4服务调用.............");
        return new Result(true,200,"查询成功", user4List);
    }
}
