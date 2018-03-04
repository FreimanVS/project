package com.freimanvs.company.websockets.clients;

import javax.websocket.*;
import java.net.URI;

@ClientEndpoint
public class RateServerWebSocketClient {

    private static Object waitLock = new Object();

    @OnMessage
    public void onMessage(String message) {
        System.out.println("Received msg: " + message);
    }

    private static void  wait4TerminateSignal() {
        synchronized(waitLock){
            try {
                waitLock.wait();
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Session session = null;
        try {
            //Tyrus is plugged via ServiceLoader API. See notes above
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            session = container.connectToServer(RateServerWebSocketClient.class, URI.create("ws://localhost:8080/rateserver"));
            wait4TerminateSignal();
        } finally{
            if(session != null){
                try {
                    session.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
