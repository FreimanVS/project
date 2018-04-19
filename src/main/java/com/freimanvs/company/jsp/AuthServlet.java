package com.freimanvs.company.jsp;

import com.freimanvs.company.service.interfaces.AuthorizationService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/auth"})
public class AuthServlet extends HttpServlet {

////    @EJB
//    @Inject
////    @EmployeeService(value = EmployeeServiceEnum.PERS)
//    private EmployeeServicePersInterface employeeService;
//
////    @EJB
//    @Inject
//    private SecurityBean securityBean;

    @Inject
    private AuthorizationService authorizationService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

//        List<Employee> employees = employeeService.getList();
//
//        boolean exists = employees != null && employees.stream().anyMatch(e -> e.getLogin().equals(login)
//                && e.getPassword().equals(securityBean.encodeSha(password)));

        if (authorizationService.isAuthorized(login, password)) {
            req.getSession().setAttribute("login", login);
            req.getRequestDispatcher("/jsp/data-base.jsp").forward(req, resp);
        } else {
            req.setAttribute("error", "Неверный логин либо пароль");
            req.getRequestDispatcher("/jsp/login.jsp").include(req, resp);
        }
    }
}
