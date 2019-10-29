package com.example.demo.service;

import com.example.demo.bean.User3;
import com.example.demo.mapper.User3Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 邵益坤
 * @Date: 2019/8/10 09:07
 * @Description:
 */
@Service
public class User3Service {

    @Autowired
    private User3Mapper user3Mapper;


    public List<User3> findAll() {
        List<User3> user3List = user3Mapper.selectList(null);
        return user3List;
    }
}
