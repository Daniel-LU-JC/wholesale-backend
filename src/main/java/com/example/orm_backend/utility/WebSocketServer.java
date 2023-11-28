package com.example.orm_backend.utility;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/websocket/transfer/{userId}")
@Component
public class WebSocketServer {

    private static final ConcurrentHashMap<String, Session> SESSIONS
            = new ConcurrentHashMap<>();

    public void sendMessage(Session toSession, String message) {
        if (toSession != null) {
            try {
                toSession.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("---对方不在线---");
        }
    }

    public void sendMessageToUser(String user, String message) {
        Session toSession = SESSIONS.get(user);
        sendMessage(toSession, message);
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("服务器收到消息：" + message);
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        if (SESSIONS.get(userId) != null) return;
        SESSIONS.put(userId, session);
        System.out.println(userId + " 上线了---");
    }

    @OnClose
    public void onClose(@PathParam("userId") String userId) {
        SESSIONS.remove(userId);
        System.out.println(userId + " 下线了---");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("ERROR!!!");
        throwable.printStackTrace();
    }
}
