package com.freimanvs.company.analytics.servlets;

import com.freimanvs.company.analytics.model.Analytics ;
import com.freimanvs.company.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/tracking")
public class TrackingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("db") != null && req.getParameter("db").equals("true")) {
            req.setAttribute("fw", new FW(req.getContextPath()));
            req.setAttribute("analytics", getAll());
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

    private List<Analytics> getAll() {

        Session session = HibernateUtil.getSessionFactory().openSession();
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

        return list;
    }
}
