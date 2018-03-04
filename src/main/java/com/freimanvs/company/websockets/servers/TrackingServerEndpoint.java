package com.freimanvs.company.websockets.servers;

import com.freimanvs.company.analytics.model.Analytics;
import com.freimanvs.company.util.HibernateUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@ServerEndpoint(value = "/trackingserver")
public class TrackingServerEndpoint {

    private static final Gson JSON = new GsonBuilder().setPrettyPrinting().create();
    private static Queue<Session> queue = new ConcurrentLinkedQueue<Session>();
    private static String cache;
    private static long sleepTime =  1000 * 60 * 5;

    private static Thread thread = new Thread(() -> {
        while(true) {
            try {
                if(queue != null) {
                    ArrayList<Session> closedSessions = new ArrayList<>();

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

                Thread.sleep(sleepTime);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    });

    private static boolean on = false;
    private synchronized static void start() {
        if (!on) {
            thread.start();
            on = true;
        }
    }

    private static String getTrackingDB() {

        org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        List<Analytics> list = null;

        try {
            Query<Analytics> query = session.createQuery("from Analytics", Analytics.class);
            list = query.getResultList();

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        if (list != null && !list.isEmpty()) {
            list.stream().forEach(a -> {
                if (a.getMarker_name() == null) {
                    a.setMarker_name("");
                }
                if (a.getLogin() == null) {
                    a.setLogin("");
                }
                if (a.getCookie() == null) {
                    a.setCookie("[]");
                }
            });
        }

        return JSON.toJson(list);
    }

    @OnMessage
    public void onMessage(Session session, String msg) {
        System.out.println("received msg " + msg + " from " + session.getId());
    }

    @OnOpen
    public void onOpen(Session session) {
        queue.add(session);
        System.out.println("New session is opened: "+session.getId());

        try {
            session.getBasicRemote().sendText(getTrackingDB());
        } catch (IOException e) {
            e.printStackTrace();
        }

        start();
    }

    @OnError
    public void onError(Session session, Throwable t) {
        queue.remove(session);
        System.err.println("Error on session " + session.getId() + ": " + t.getLocalizedMessage());
    }

    @OnClose
    public void onClose(Session session) {
        queue.remove(session);
        System.out.println("New session is closed: "+session.getId());
    }
}
