package com.freimanvs.company.servlets;

import com.freimanvs.company.entities.Employee;
import com.freimanvs.company.service.EmployeeService;
import com.freimanvs.company.service.Service;
import com.freimanvs.company.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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

    private Service<Employee> employeeService = new EmployeeService();
    private static final Jsonb JSONB = JsonbBuilder.create();

    public EmployeeServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        //get all
        if (id == null) {

            List<Employee> employees = employeeService.getList();
            String jsonString = JSONB.toJson(employees);

            resp.setContentType("application/json");
            try (PrintWriter pw = resp.getWriter()) {
                pw.print(jsonString);
                pw.flush();
            }
        }

        //get one by id
        else {

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contentType = req.getContentType();
        if (!contentType.equals("application/json")) {
            resp.sendError(400, "application/json is required");
            return;
        }

        try (InputStream is = req.getInputStream();
            PrintWriter pw = resp.getWriter()) {
            StringBuilder json = new StringBuilder();
            while (is.available() != 0) {
                json.append((char)is.read());
            }
            Employee emp = JSONB.fromJson(json.toString(), Employee.class);

            long id = employeeService.add(emp);

//            Employee empFromDB = employeeService.getById(id);
//            System.out.println(JSONB.toJson(empFromDB));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String contentType = req.getContentType();
        if (!contentType.startsWith("application/json")) {
            resp.sendError(400, "application/json is required");
            return;
        }

        long id_param = Long.valueOf(req.getParameter("id"));
        try (InputStream is = req.getInputStream();
             PrintWriter pw = resp.getWriter()) {
            StringBuilder json = new StringBuilder();
            while (is.available() != 0) {
                json.append((char)is.read());
            }
            Employee emp = JSONB.fromJson(json.toString(), Employee.class);
            employeeService.updateById(id_param, emp);
//            System.out.println(JSONB.toJson(employeeService.getById(id_param)));
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id_param = Long.valueOf(req.getParameter("id"));
        employeeService.deleteById(id_param);
//        resp.sendRedirect("/login");
    }
}