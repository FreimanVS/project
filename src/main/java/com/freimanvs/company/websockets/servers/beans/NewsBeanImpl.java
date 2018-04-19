package com.freimanvs.company.websockets.servers.beans;

import com.freimanvs.company.html.beans.interfaces.JsoupBean;
import com.freimanvs.company.websockets.servers.beans.interfaces.NewsBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.websocket.Session;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Singleton
//@Dependent
public class NewsBeanImpl implements NewsBean {

    private final Gson JSON = new GsonBuilder().setPrettyPrinting().create();
    private Queue<Session> queue = new ConcurrentLinkedQueue<>();
    private String cache;
    private long sleepTime;

//    @EJB
    @Inject
    private JsoupBean jsoupBean;

    @Resource
    private TimerService timerService;

    @Override
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
//    @Schedule(hour = "*", minute = "*", second = "*/3", info = "Every 3 seconds timer")
    public void timeout() {
        System.out.println("News websocket bean, SLEEPTIME: " + sleepTime);
        try {
            if(queue != null) {
                ArrayList<Session> closedSessions = new ArrayList<>();

                String result = getNews();
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

    public String getNews() {
        sleepTime = Long.parseLong(System.getenv("SLEEP_TIME_JAVA"));

        return JSON.toJson(jsoupBean.getNews());
    }

    public Queue<Session> getQueue() {
        return queue;
    }
}
