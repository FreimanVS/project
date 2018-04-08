package com.freimanvs.company.websockets.servers.beans.interfaces;

import javax.ejb.Remote;
import javax.websocket.Session;
import java.util.Queue;

@Remote
public interface NewsBean {
    void start();
    String getNews();
    Queue<Session> getQueue();
    void closeTimers();
}
