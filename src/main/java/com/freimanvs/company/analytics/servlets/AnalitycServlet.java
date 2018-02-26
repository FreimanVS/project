package com.freimanvs.company.analytics.servlets;

import com.freimanvs.company.analytics.model.Analytics ;
import com.freimanvs.company.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;

@WebServlet("/analytics")
public class AnalitycServlet extends HttpServlet {

    private static final Jsonb JSONB = JsonbBuilder.create();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long time = Long.parseLong(req.getParameter("time"));

        String marker_name = System.getenv("MARKER_NAME");
        String jsp_name = req.getParameter("jsp");
        String ip = req.getRemoteAddr();
        String browser_info = req.getHeader("User-Agent");
        Timestamp client_time = new Timestamp(time);
        Timestamp server_time = Timestamp.from(Calendar.getInstance().toInstant());
        String login = req.getSession().getAttribute("login") == null ? null : (String) req.getSession().getAttribute("login");

        String cookie = cookiesToString(req.getCookies());

        Object obj = req.getSession().getAttribute("prevId");
        long prev_id = obj == null ? 0L : JSONB.fromJson((String)obj, Analytics.class).getId();
        long curId = add(marker_name, jsp_name, ip, browser_info,
                client_time, server_time, login, cookie, prev_id);

        //toJson
        Analytics analytics = new Analytics();
        analytics.setId(curId);
        String json = JSONB.toJson(analytics);

        req.getSession().setAttribute("prevId", json);

        try (PrintWriter pw = resp.getWriter()) {
            pw.print(json);
        }
    }

    private String cookiesToString(Cookie[] cookie) {
        StringBuilder sb = new StringBuilder("[");
        Arrays.stream(cookie).forEach(c -> sb.append("{").append(c.getName())
                .append(": ").append(c.getValue()).append("}, "));
        if (sb.length() > 0) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]");
        return sb.toString();
    }

    private long add(String marker_name, String jsp_name, String ip, String browser_info,
                     Timestamp client_time, Timestamp server_time, String login,
            String cookie, long prev_id) {
        Analytics analitycs = new Analytics();
        analitycs.setMarker_name(marker_name);
        analitycs.setJsp_name(jsp_name);
        analitycs.setIp(ip);
        analitycs.setBrowser_info(browser_info);
        analitycs.setClient_time(client_time);
        analitycs.setServer_time(server_time);
        analitycs.setLogin(login);
        analitycs.setCookie(cookie);
        analitycs.setPrev_id(prev_id);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        long id = 0L;

        try {
            session.save(analitycs);
            id = analitycs.getId();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return id;
    }
}
