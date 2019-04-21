package org.websocket;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @author zsw
 * @date 2019/4/19 10:24
 */
@ServerEndpoint(value = "/websocket",configurator = GetHttpSessionConfigurator.class)
public class WebSocketServer {
    /**
     * 与客户端的连接会话，需通过它给客户端发送数据
     */
    private Session session;
    private int userId;
    @OnOpen
    public void onOpen(Session session, EndpointConfig config){
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        int userId = (int) httpSession.getAttribute("userId");
        this.userId = userId;
        this.session = session;
        WebSocketServerManager.add(userId,this);
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    @OnClose
    public void onClose(){
        WebSocketServerManager.remove(this.userId);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
    }

    @OnError
    public void onError(Session session,Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }

}
