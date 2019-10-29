package cn.itcast.shiro.handler;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: 邵益坤
 * @Date: 2019/10/20 20:37
 * @Description:
 */
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public String error(HttpServletRequest request, HttpServletResponse response){
        return "未授权";
    }


}
