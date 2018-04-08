package com.freimanvs.company.analytics.servlets;

import com.freimanvs.company.analytics.beans.interfaces.AnalyticsBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tracking")
public class TrackingServlet extends HttpServlet {

    @EJB
    private AnalyticsBean analyticsBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if ("true".equals(req.getParameter("db"))) {
            req.setAttribute("fw", new FW(req.getContextPath()));
            req.setAttribute("analytics", analyticsBean.getAll());
            getServletContext().getRequestDispatcher("/ftl/analytics.ftl").forward(req, resp);
        } else {
            String tracking = req.getParameter("tracking");
            getServletContext().setAttribute("tracking", tracking);
            req.getRequestDispatcher("/jsp/data-base.jsp").include(req, resp);
        }
    }

    public class FW {
        String contextPath;
        FW(String contextPath) {
            this.contextPath = contextPath;
        }
        public String getContextPath() {
            return contextPath;
        }
    }
}
