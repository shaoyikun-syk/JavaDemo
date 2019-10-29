package com.example.demo.vo;


import lombok.*;

/**
 * @Author: 邵益坤
 * @Date: 2019/8/10 08:44
 * @Description:
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString


public class Result {
    private boolean flag;//是否成功
    private Integer code;// 返回码
    private String message;//返回信息

    private Object obj;// 返回数据

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }
}
