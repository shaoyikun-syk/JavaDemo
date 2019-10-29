package com.example.demo.controller;

import com.example.demo.bean.User3;
import com.example.demo.service.User3Service;
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
@RequestMapping("user3")
public class User3Controller {

    @Autowired
    private User3Service user3Service;

    @GetMapping
    public Result findAll(){

        List<User3> user3List = user3Service.findAll();
        System.out.println("user3服务调用.............");
        return new Result(true,200,"查询成功", user3List);
    }
}
