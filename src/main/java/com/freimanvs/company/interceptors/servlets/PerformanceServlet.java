package com.freimanvs.company.interceptors.servlets;

import com.freimanvs.company.interceptors.dao.interfaces.PerformanceDAO;
import com.freimanvs.company.interceptors.models.Performance;

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
import java.util.List;

@WebServlet("/performance")
public class PerformanceServlet extends HttpServlet {

    @EJB
    private PerformanceDAO performanceDAO;

    private static final Jsonb JSONB = JsonbBuilder.create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Performance> list = performanceDAO.getList();
        String response = JSONB.toJson(list);

        resp.setContentLength(response.length());
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf8");
        resp.setStatus(200);

        try (PrintWriter pw = resp.getWriter()) {
            pw.println(response);
        }

    }
}
