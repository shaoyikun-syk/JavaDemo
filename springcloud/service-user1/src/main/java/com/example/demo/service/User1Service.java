package com.example.demo.service;

import com.example.demo.bean.User1;
import com.example.demo.mapper.User1Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 邵益坤
 * @Date: 2019/8/10 09:07
 * @Description:
 */
@Service
public class User1Service {

    @Autowired
    private User1Mapper user1Mapper;


    public List<User1> findAll() {
        List<User1> user1List = user1Mapper.selectList(null);
        return user1List;
    }
}
