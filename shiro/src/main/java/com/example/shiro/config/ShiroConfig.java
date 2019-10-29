package com.example.shiro.config;

import com.example.shiro.shiro.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: 邵益坤
 * @Date: 2019/10/16 19:08
 * @Description:
 */

@Configuration
public class ShiroConfig {


    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("dwsm") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        factoryBean.setSecurityManager(defaultWebSecurityManager);

        //添加shiro内置过滤器
        /**
         * shiro内置过滤器,可以实现权限相关的拦截器
         * 常用的过滤器：
         * anon:无需认证
         * authc：必须认证才可以访问
         * user：如果使用rememberMe的功能可以直接访问
         * perms:该资源必须得到资源权限才可以访问
         * role:该资源必须得到角色权限才可以访问
         */

        Map<String, String> map = new LinkedHashMap<>();


        //配置拦截
        //map.put("/user/add", "authc");//http://localhost:8080/user/add   拦截
        //map.put("/user/update", "authc");



        //map.put("/**", "authc");
        map.put("/user/toLogin", "anon");//放行跳转登录页面
        map.put("/user/login", "anon");//放行登录接口
        map.put("/**", "authc");


        factoryBean.setLoginUrl("/user/toLogin");



        factoryBean.setFilterChainDefinitionMap(map);
        return factoryBean;

    }


    @Bean("dwsm")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("UserRealm") UserRealm userRealm){

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联
        securityManager.setRealm(userRealm);

        return securityManager;
    }


    //创建自定义认证类
    @Bean("UserRealm")
    public UserRealm getUserRealm(){
        return new UserRealm();
    }
}
