package com.example.shiro.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @Author: 邵益坤
 * @Date: 2019/10/16 19:05
 * @Description:
 */
public class UserRealm extends AuthorizingRealm{

    //执行授权逻辑
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        return null;
    }



    //执行认证逻辑
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("执行认证逻辑");
        UsernamePasswordToken token=(UsernamePasswordToken)authenticationToken;
        if(!token.getUsername().equals("admin")){
            return null;
        }




        return new SimpleAuthenticationInfo("","123","");
    }
}
