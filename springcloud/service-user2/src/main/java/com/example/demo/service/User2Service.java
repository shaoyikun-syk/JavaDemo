package com.example.demo.service;

import com.example.demo.bean.User2;
import com.example.demo.mapper.User2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 邵益坤
 * @Date: 2019/8/10 09:07
 * @Description:
 */
@Service
public class User2Service {

    @Autowired
    private User2Mapper user2Mapper;


    public List<User2> findAll() {
        List<User2> user2List = user2Mapper.selectList(null);
        return user2List;
    }
}
