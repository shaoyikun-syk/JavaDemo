package cn.itcast.shiro.session;

/**
 * @Author: 邵益坤
 * @Date: 2019/10/20 21:04
 * @Description:
 */

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * 自定义的sessionManager
 */
public class CustomSessionManager extends DefaultWebSessionManager{

    /**
     * 头信息中具有sessionid
     *      请求头：Authorization： sessionid
     *
     * 指定sessionId的获取方式
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {

        String id= WebUtils.toHttp(request).getHeader("Authorization");//ServletRequest=>HttpServletRequest
        if(StringUtils.isEmpty(id)){
            //如果没有携带，生成新的sessionId
            return super.getSessionId(request,response);//让父类生成
        }else{
            //返回sessionId；
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "header");//存放请求头
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);//sessionId的值
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);//session要不要验证
            return id;
        }

    }
}
