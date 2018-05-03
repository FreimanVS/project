package com.freimanvs.company.websockets.servers;


import com.freimanvs.company.websockets.servers.beans.interfaces.TrackingBean;

import javax.ejb.EJB;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/trackingserver")
public class TrackingServerEndpoint {

    @EJB
    private TrackingBean trackingBean;

    @OnMessage
    public void onMessage(Session session, String msg) {
    }

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        trackingBean.getQueue().add(session);

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
    }

    @OnClose
    public void onClose(Session session) {
        trackingBean.getQueue().remove(session);
    }
}
