package com.freimanvs.company.jsp;

import com.freimanvs.company.dao.interfaces.EmployeeDAOPersInterface;
import com.freimanvs.company.entities.Employee;
import com.freimanvs.company.service.interfaces.AdminService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ServletSecurity(httpMethodConstraints = {
        @HttpMethodConstraint(rolesAllowed = {"admin"}, value = "GET"),
        @HttpMethodConstraint(rolesAllowed = {"admin"}, value = "POST")
})
@WebServlet(urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet {

    @EJB
    private AdminService adminService;

    @EJB
    private EmployeeDAOPersInterface employeeDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/admin-page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        Employee emp = employeeDAO.getByUnique("login", login);
        if (emp == null) {
            req.setAttribute("noLogin", "there is not a user with such login");
            req.getRequestDispatcher("/jsp/data-base.jsp").include(req, resp);
        } else {
            req.setAttribute("noLogin", "");

            String roles = req.getParameter("roles");
            String[] rolesArr = roles.split("[&]{1}");

            adminService.assignRoles(login, rolesArr);
            req.getRequestDispatcher("/jsp/data-base.jsp").forward(req, resp);
        }
    }
}
