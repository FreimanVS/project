package com.freimanvs.company.servlets;

import com.freimanvs.company.entities.Employee;
import com.freimanvs.company.service.interfaces.EmployeeServicePersInterface;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {

//    @EJB
    @Inject
//    @EmployeeService(value = EmployeeServiceEnum.PERS)
    private EmployeeServicePersInterface employeeService;

    private static final Jsonb JSONB = JsonbBuilder.create();

    public EmployeeServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        String response;

        //get all
        if (id == null) {

            List<Employee> employees = employeeService.getList();
            response = JSONB.toJson(employees);

            resp.setContentLength(response.length());
            resp.setContentType("application/json");
            resp.setCharacterEncoding("utf8");
            resp.setStatus(200);
            try (PrintWriter pw = resp.getWriter()) {
                pw.print(response);
                pw.flush();
            }
        }

        //get one by id
        else {
            Employee employee = employeeService.getById(Long.parseLong(id));

            if (employee == null) {
                notFound(resp);
            } else {
                response = JSONB.toJson(employee);
                resp.setStatus(200);
                resp.setContentType("application/json");
                resp.setCharacterEncoding("utf8");
                resp.setContentLength(response.length());
                try (PrintWriter pw = resp.getWriter()) {
                    pw.print(response);
                    pw.flush();
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contentType = req.getContentType();
        if (!contentType.contains("application/json")) {
            badRequest(resp);
            return;
        }

        try (InputStream is = req.getInputStream()) {
            StringBuilder json = new StringBuilder();
            while (is.available() != 0) {
                json.append((char)is.read());
            }
            Employee emp = JSONB.fromJson(json.toString(), Employee.class);

            long id = employeeService.add(emp);

            String absolute_path = req.getProtocol().split("[/]")[0].toLowerCase() + "://" + req.getLocalAddr() +
                    ":" + req.getLocalPort() + req.getRequestURI() + "?id=" + id;
            resp.setHeader("location", absolute_path);
            resp.setStatus(201);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String contentType = req.getContentType();
        if (!contentType.startsWith("application/json")) {
            badRequest(resp);
            return;
        }

        long id_param = Long.valueOf(req.getParameter("id"));

        Employee employee = employeeService.getById(id_param);
        if (employee == null) {
            notFound(resp);
        } else {

            try (InputStream is = req.getInputStream()) {
                StringBuilder json = new StringBuilder();
                while (is.available() != 0) {
                    json.append((char) is.read());
                }
                Employee emp = JSONB.fromJson(json.toString(), Employee.class);

                employeeService.updateById(id_param, emp);

                String absolute_path = req.getProtocol().split("[/]")[0].toLowerCase() + "://" +
                        req.getLocalAddr() + ":" + req.getLocalPort() + req.getRequestURI() + "?id=" + id_param;
                resp.setHeader("location", absolute_path);
                resp.setStatus(201);
            }
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id_param = Long.valueOf(req.getParameter("id"));

        Employee employee = employeeService.getById(id_param);

        if (employee == null) {
            notFound(resp);
            return;
        }

        employeeService.deleteById(id_param);
        resp.setStatus(204);
    }

    private static void notFound(HttpServletResponse resp) throws IOException {
        String response = "HTTP 404 Not Found";
        resp.sendError(404);
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf8");
        resp.setContentLength(response.length());
        resp.setHeader("connection", "close");

        try (PrintWriter pw = resp.getWriter()) {
            pw.print(response);
            pw.flush();
        }
    }

    private static void badRequest(HttpServletResponse resp) throws IOException {
        String response = "application/json is required";
        resp.sendError(400, response);
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf8");
        resp.setContentLength(response.length());
        resp.setHeader("connection", "close");
    }
}