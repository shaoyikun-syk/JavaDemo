package com.example.demo.service;

import com.example.demo.bean.User4;
import com.example.demo.mapper.User4Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 邵益坤
 * @Date: 2019/8/10 09:07
 * @Description:
 */
@Service
public class User4Service {

    @Autowired
    private User4Mapper user4Mapper;


    public List<User4> findAll() {
        List<User4> user4List = user4Mapper.selectList(null);
        return user4List;
    }
}
