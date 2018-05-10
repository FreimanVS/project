package com.freimanvs.company.analytics.servlets;

import com.freimanvs.company.analytics.beans.interfaces.AnalyticsBean;
import com.freimanvs.company.analytics.model.Analytics ;

import javax.ejb.EJB;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Calendar;

@WebServlet("/analytics")
public class AnalitycServlet extends HttpServlet {

    @EJB
    private AnalyticsBean analyticsBean;

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
        String login = req.getUserPrincipal() != null ? req.getUserPrincipal().getName() : "";
//                req.getSession().getAttribute("login") == null ? null : (String) req.getSession().getAttribute("login");

        String cookie = analyticsBean.cookiesToString(req.getCookies());

        Object obj = req.getSession().getAttribute("prevId");
        long prev_id = obj == null ? 0L : JSONB.fromJson((String)obj, Analytics.class).getId();
        long curId = analyticsBean.add(marker_name, jsp_name, ip, browser_info,
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
}
