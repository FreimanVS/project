package com.freimanvs.company.websockets.servers;

import com.freimanvs.company.html.ObjForJson;
import com.freimanvs.company.websockets.servers.config.ServletAwareConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@ServerEndpoint(value="/jsoupserver")
public class JsoupServerEndpoint {
    private static final Gson JSON = new GsonBuilder().setPrettyPrinting().create();
    private static Queue<Session> queue = new ConcurrentLinkedQueue<Session>();
    private static String cache;
    private static long sleepTime;

    private static Thread thread = new Thread(() -> {
        while(true) {
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

    private static String getNews() {
        sleepTime = Long.parseLong(System.getenv("SLEEP_TIME_JAVA"));
        Document doc;
        try {
            doc = Jsoup.connect("https://www.rbc.ru").get();
            Elements elems = doc.getElementsByClass("main-feed__item js-main-reload-item");

            List news = new ArrayList<>(elems);

            List<ObjForJson> list = new ArrayList<>();
            news.forEach(n -> list.add(new ObjForJson(n.toString())));
            return JSON.toJson(list);

        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    @OnMessage
    public void onMessage(Session session, String msg) {
        System.out.println("received msg " + msg + " from " + session.getId());
    }

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        queue.add(session);
        System.out.println("New session is opened: "+session.getId());

        try {
            session.getBasicRemote().sendText(getNews());
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
