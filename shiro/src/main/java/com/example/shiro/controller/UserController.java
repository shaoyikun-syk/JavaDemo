package com.example.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: 邵益坤
 * @Date: 2019/10/16 19:21
 * @Description:
 */
@Controller
@RequestMapping("user")
public class UserController {


    @RequestMapping("/hello")
    public String hello(Model model){

        model.addAttribute("name", "邵益坤");

        return "test";
    }


    /*@RequestMapping(value = "/login")
    public void a(){

    }*/

    @RequestMapping(value = "/login")
    public String login(String pwd,Model model){


        System.out.println("登录验证.............");
        System.out.println(pwd);

        //获取subject
        Subject subject = SecurityUtils.getSubject();

        //封装用户数据
        UsernamePasswordToken token=new UsernamePasswordToken("admin",pwd);

        try {
            subject.login(token);

            //无异常登录成功

            return "redirect:test";

        } catch (UnknownAccountException e) {//AuthenticationException
            e.printStackTrace();
            model.addAttribute("msg", "用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            model.addAttribute("msg", "密码错误");
            return "login";
        }catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "错误");
            return "login";
        }



    }


    @RequestMapping("/add")
    public String add(){
        return "add";
    }

    @RequestMapping("/update")
    public String update(){
        return "update";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/autherror")
    public String autherror(int code){
        return code==1?"未登录":"未授权";
    }

}
