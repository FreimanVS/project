package com.freimanvs.company.observerevents;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/test")
public class TestServlet extends HttpServlet {

    @Inject
    private Event<TestObj> event;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String host = req.getLocalAddr();
        if (host.contains("0:0:0:0:0:0:1")) {
            host = "localhost";
        }
        String port = String.valueOf(req.getLocalPort());
        String contextPath = req.getContextPath();

        event.fire(new TestObj(host, port, contextPath));

        try (PrintWriter pw = resp.getWriter()) {
            pw.println("test servlet");
        }
    }
}
