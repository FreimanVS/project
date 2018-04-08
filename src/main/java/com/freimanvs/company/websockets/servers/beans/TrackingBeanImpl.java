package com.freimanvs.company.websockets.servers.beans;

import com.freimanvs.company.analytics.beans.interfaces.AnalyticsBean;
import com.freimanvs.company.websockets.servers.beans.interfaces.TrackingBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Singleton
public class TrackingBeanImpl implements TrackingBean {

    @EJB
    private AnalyticsBean analyticsBean;

    @Resource
    private TimerService timerService;

    private Queue<Session> queue = new ConcurrentLinkedQueue<>();
    private String cache;
    private long sleepTime = Long.parseLong(System.getenv("SLEEP_TIME_JAVA"));


    private final Gson JSON = new GsonBuilder().setPrettyPrinting().create();

    public String getTrackingDB() {
        return JSON.toJson(analyticsBean.getAll());
    }

    public void start() {
        if (timerService.getTimers().isEmpty()) {
            timerService.createTimer(0, sleepTime, "Interval Timer: " + sleepTime + "ms.");
        }
    }

    @Override
    public void closeTimers() {
        timerService.getTimers().forEach(Timer::cancel);
    }

    @Timeout
    public void timeOut() {
        try {
            System.out.println("TRACKING");
            if(queue != null) {
                ArrayList<Session> closedSessions = new ArrayList<>();

                sleepTime = Long.parseLong(System.getenv("SLEEP_TIME_JAVA"));
                String result = getTrackingDB();
                if (!result.equals(cache)) {
                    for (Session session : queue) {
                        if(!session.isOpen()) {
                            closedSessions.add(session);
                        } else {
                            session.getBasicRemote().sendText(result);
                        }
                    }
                    cache = result;
                } else {
                    for (Session session : queue) {
                        if(!session.isOpen()) {
                            closedSessions.add(session);
                        } else {
                            session.getBasicRemote().sendText("[]");
                        }
                    }
                }
                queue.removeAll(closedSessions);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Queue<Session> getQueue() {
        return queue;
    }
}
