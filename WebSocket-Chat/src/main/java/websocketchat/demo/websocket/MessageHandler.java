package websocketchat.demo.websocket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import websocketchat.demo.dao.MessageDao;
import websocketchat.demo.pojo.Message;
import websocketchat.demo.pojo.UserData;

import java.util.HashMap;
import java.util.Map;

@Component
public class MessageHandler extends TextWebSocketHandler {

    @Autowired
    private MessageDao messageDAO;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    //这个map存入redis会有问题  WebSocketSession不能序列化
    private static final Map<Long,WebSocketSession> SESSIONS = new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        System.out.println("欢迎连接WS服务");
        Long uid = (Long) session.getAttributes().get("uid");
        System.out.println("session取出uid");
        // 将当前用户的session放置到map中，后面会使用相应的session通信
        SESSIONS.put(uid, session);
        System.out.println("存入SESSIONS  后面会使用相应的session通信");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage
            textMessage) throws Exception {


        System.out.println("获取到消息");

        Long uid = (Long) session.getAttributes().get("uid");//发送者id
        System.out.println("MAPPER         ObjectMapper");

        System.out.println(textMessage.getPayload());
        JsonNode jsonNode = MAPPER.readTree(textMessage.getPayload());//转化为json
        Long toId = jsonNode.get("toId").asLong();
        String msg = jsonNode.get("msg").asText();

        Message message = Message.builder()
                .from(UserData.USER_MAP.get(uid))
                .to(UserData.USER_MAP.get(toId))
                .msg(msg)
                .build();
        /**
         *  USER_MAP.put(1001L, User.builder().id(1001L).username("zhangsan").build());
         USER_MAP.put(1002L, User.builder().id(1002L).username("lisi").build());
         */

        // 将消息保存到MongoDB
        message = this.messageDAO.saveMessage(message);

        // 判断to用户是否在线
        WebSocketSession toSession = SESSIONS.get(toId);
        if (toSession != null && toSession.isOpen()) {
            //TODO 具体格式需要和前端对接
            toSession.sendMessage(new
                    TextMessage(MAPPER.writeValueAsString(message)));
            // 更新消息状态为已读
            this.messageDAO.updateMessageState(message.getId(), 2);
        }

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("webSocket断开服务");
    }
}
