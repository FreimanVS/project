package com.freimanvs.company.jsp;

import com.freimanvs.company.entities.Employee;
import com.freimanvs.company.security.beans.interfaces.SecurityBean;
import com.freimanvs.company.service.interfaces.EmployeeServicePersInterface;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/auth"})
public class AuthServlet extends HttpServlet {

    @EJB
    private EmployeeServicePersInterface employeeService;

    @EJB
    private SecurityBean securityBean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        List<Employee> employees = employeeService.getList();

        boolean exists = employees != null && employees.stream().anyMatch(e -> e.getLogin().equals(login)
                && e.getPassword().equals(securityBean.encodeSha(password)));

        if (exists) {
            req.getSession().setAttribute("login", login);
            req.getRequestDispatcher("/jsp/data-base.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Неверный логин либо пароль");
            req.getRequestDispatcher("/jsp/login.jsp").include(req, resp);
        }
    }
}
