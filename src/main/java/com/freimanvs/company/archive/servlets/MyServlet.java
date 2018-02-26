package com.freimanvs.company.archive.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(urlPatterns = {"/myservlet"})
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletConfig();

        getServletContext();
        getServletContext().getSessionCookieConfig();

        req.getCookies();
        resp.addCookie(new Cookie("key", "value"));

        req.getSession();

        req.getRequestDispatcher("/com/freimanvs/company/jsp/index.jsp").forward(req, resp);
        req.getRequestDispatcher("/com/freimanvs/company/jsp/index.jsp").include(req, resp);
        resp.sendRedirect("http://www.google.com");
    }
}
