package com.freimanvs.company.websockets.servers;


import com.freimanvs.company.websockets.servers.beans.interfaces.TrackingBean;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/trackingserver")
public class TrackingServerEndpoint {

    @EJB
//    @Inject
    private TrackingBean trackingBean;

    @OnMessage
    public void onMessage(Session session, String msg) {
        System.out.println("received msg " + msg + " from " + session.getId());
    }

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        trackingBean.getQueue().add(session);
        System.out.println("New session is opened: "+session.getId());

        try {
            session.getBasicRemote().sendText(trackingBean.getTrackingDB());
        } catch (IOException e) {
            e.printStackTrace();
        }

        trackingBean.start();
    }

    @OnError
    public void onError(Session session, Throwable t) {
        trackingBean.getQueue().remove(session);
//        trackingBean.closeTimers();
        System.err.println("Error on session " + session.getId() + ": " + t.getLocalizedMessage());
    }

    @OnClose
    public void onClose(Session session) {
        trackingBean.getQueue().remove(session);
//        trackingBean.closeTimers();
        System.out.println("New session is closed: "+session.getId());
    }
}
