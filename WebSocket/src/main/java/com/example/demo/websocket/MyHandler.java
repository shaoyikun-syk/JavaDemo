package com.example.demo.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @Author: 邵益坤
 * @Date: 2019/10/14 13:16
 * @Description:
 */

@Component
public class MyHandler extends TextWebSocketHandler{


    //测试地址:  http://coolaf.com/tool/chattest

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        System.out.println("获取到的消息:"+message.getPayload());

        session.sendMessage(new TextMessage("消息已经收到"));
        if(message.getPayload().equals("msg")){
            for (int i = 0; i < 10; i++) {
                session.sendMessage(new TextMessage("消息:"+i));
                Thread.sleep(500);

            }
        }

    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        session.sendMessage(new TextMessage("欢迎连接WS服务"));

        Object id = session.getAttributes().get("id");
        System.out.println("你好id"+id);

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        session.sendMessage(new TextMessage("断开WS服务"));
    }
}















