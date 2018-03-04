package com.freimanvs.company.websockets.servers;

import com.freimanvs.company.util.FileManager;
import com.freimanvs.company.websockets.models.Valcurs;
import com.freimanvs.company.websockets.servers.config.ServletAwareConfig;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@ServerEndpoint(value = "/rateserver", configurator=ServletAwareConfig.class)
public class RateServerEndpoint {

    private static EndpointConfig config;
    private static Queue<Session> queue = new ConcurrentLinkedQueue<Session>();

    private static String cache;
    private static long sleepTime =  1000 * 60 * 5;
    private static Thread rateThread = new Thread(() -> {
        while(true) {
            try {
                if(queue != null) {
                    ArrayList<Session> closedSessions = new ArrayList<>();

                    String result = getRate();
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
                                session.getBasicRemote().sendText("{\"valute\": []}");
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
            rateThread.start();
            on = true;
        }
    }

    private static String getRate() {
        HttpSession httpSession = (HttpSession) config.getUserProperties().get("httpSession");
        ServletContext servletContext = httpSession.getServletContext();

        String XSL = "/xslt/XML_daily.asp.xsl";
        String XML = "http://www.cbr.ru/scripts/XML_daily.asp";

        try {
            StreamSource stylesource = new StreamSource(servletContext.getResourceAsStream(XSL));
            StreamSource xmlsource = new StreamSource(new URL(XML).openStream());

            Transformer transformer = TransformerFactory.newInstance().newTransformer(stylesource);

            transformer.transform(xmlsource, new StreamResult(new File("./result.txt")));

            return FileManager.xmlToJSON("./result.txt", Valcurs.class);
        } catch (IOException | TransformerException e) {
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
        this.config = config;
        queue.add(session);
        System.out.println("New session is opened: "+session.getId());

        try {
            session.getBasicRemote().sendText(getRate());
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
