package com.freimanvs.company.websockets.servers;

import com.freimanvs.company.websockets.servers.beans.interfaces.NewsBean;

import javax.ejb.EJB;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value="/jsoupserver")
public class JsoupServerEndpoint {

    @EJB
    private NewsBean newsBean;

    @OnMessage
    public void onMessage(Session session, String msg) {
        System.out.println("received msg " + msg + " from " + session.getId());
    }

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        newsBean.getQueue().add(session);
        System.out.println("New session is opened: "+session.getId());

        try {
            session.getBasicRemote().sendText(newsBean.getNews());
        } catch (IOException e) {
            e.printStackTrace();
        }

        newsBean.start();
    }

    @OnError
    public void onError(Session session, Throwable t) {
        newsBean.getQueue().remove(session);
        newsBean.closeTimers();
        System.err.println("Error on session " + session.getId() + ": " + t.getLocalizedMessage());
    }

    @OnClose
    public void onClose(Session session) {
        newsBean.getQueue().remove(session);
        newsBean.closeTimers();
        System.out.println("New session is closed: "+session.getId());
    }
}
