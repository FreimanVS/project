package com.freimanvs.company.jsp;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ServletSecurity(httpMethodConstraints = {
        @HttpMethodConstraint(rolesAllowed = {}, value = "GET")
})
@WebServlet(urlPatterns = {"/discounts"})
public class DiscountsServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/discounts.jsp").forward(req, resp);
    }
}
