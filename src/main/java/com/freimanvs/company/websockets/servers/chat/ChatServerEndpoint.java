package com.freimanvs.company.websockets.servers.chat;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(value="/chatserver", configurator= ChatServerEndpointConfigurator.class)
public class ChatServerEndpoint {

    private Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    @OnMessage
    public void onMessage(String message, Session userSession) {
        for (Session session : sessions) {
            session.getAsyncRemote().sendText(message);
        }
    }
}