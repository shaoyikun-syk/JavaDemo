package com.example.demo.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @Author: 邵益坤
 * @Date: 2019/8/10 09:11
 * @Description:
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


@TableName("user2")
public class User2 {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
}
