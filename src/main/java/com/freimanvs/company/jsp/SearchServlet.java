package com.freimanvs.company.jsp;

import com.freimanvs.company.entities.Employee;
import com.freimanvs.company.jsp.beans.interfaces.SearchBean;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.TimeUnit;

@ServletSecurity(httpMethodConstraints = {
        @HttpMethodConstraint(rolesAllowed = {"admin"}, value = "POST")
})
@WebServlet(urlPatterns = {"/search"}, name = "searchServlet", asyncSupported = true)
public class SearchServlet extends HttpServlet {

    @EJB
    private SearchBean searchBean;

    @Resource
    private ManagedScheduledExecutorService scheduledExecutorService;

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final AsyncContext asyncContext = req.startAsync();

        scheduledExecutorService.schedule(() -> {
                    search(asyncContext);
                    return null;
                },
                0,
                TimeUnit.MILLISECONDS);

    }

    public void search(final AsyncContext context) throws ServletException, IOException {

        HttpServletRequest req = (HttpServletRequest)context.getRequest();
        HttpServletResponse resp = (HttpServletResponse)context.getResponse();

        StringBuilder resultTable = new StringBuilder();

        //if cache or not
        Enumeration<String> paramNames = req.getParameterNames();
        StringBuilder fullParamRequest = new StringBuilder(req.getMethod() + req.getRequestURI());
        while (paramNames.hasMoreElements()) {
            String paramKey = paramNames.nextElement();
            String paramValue = req.getParameter(paramKey);
            fullParamRequest.append(paramKey).append(paramValue);
        }

        //if cache
        Object contextAttrValue = getServletContext().getAttribute(fullParamRequest.toString());
        if (contextAttrValue != null && contextAttrValue instanceof String) {
            resultTable.append((String)contextAttrValue);

            System.out.println("data from CACHE");
        }

        //if not
        else {
            String login = req.getParameter("login");
            String fio = req.getParameter("fio");
            String pos = req.getParameter("position");
            String city = req.getParameter("city");
            String ageFromString = req.getParameter("ageFrom");
            String ageToString = req.getParameter("ageTo");
            int ageFrom = ageFromString.equals("") ? 1 : Integer.parseInt(ageFromString);
            int ageTo = ageToString.equals("") ? 999 : Integer.parseInt(ageToString);

            List<Employee> list = searchBean.getBy(login, fio, pos, city, ageFrom, ageTo);

            if (list != null && !list.isEmpty()) {
                resultTable.append("<table><caption><b>РАБОТНИКИ</b></caption>" +
                        "<thead><tr>" +
                        "<th>id</th>" +
                        "<th>Логин</th>" +
                        "<th>ФИО</th>" +
                        "<th>Должность</th>" +
                        "<th>Роль</th>" +
                        "<th>Город</th>" +
                        "<th>Зарплата</th>" +
                        "<th>Номер телефона</th>" +
                        "<th>email</th>" +
                        "<th>Возраст</th>" +
                        "</tr></thead><tbody>");

                list.forEach(e -> {
                    StringBuilder positions = new StringBuilder("<select>");
                    e.getPositions().forEach(p -> positions.append("<option>").append(p.getName())
                            .append("</option>"));
                    positions.append("</select>");

                    StringBuilder roles = new StringBuilder("<select>");
                    e.getRoles().forEach(r -> roles.append("<option>").append(r.getName())
                            .append("</option>"));
                    roles.append("</select>");

                    resultTable.append("<tr>"
                            + "<td align=\"center\">"+ e.getId() + "</td>"
                            + "<td align=\"center\">" + e.getLogin() + "</td>"
                            + "<td align=\"center\">" + e.getFio() + "</td>"
                            + "<td align=\"center\">" + positions.toString() + "</td>"
                            + "<td align=\"center\">" + roles.toString() + "</td>"
                            + "<td align=\"center\">" + e.getCity() + "</td>"
                            + "<td align=\"center\">" + e.getSalary() + "</td>"
                            + "<td align=\"center\">" + e.getPhoneNumber() + "</td>"
                            + "<td align=\"center\">" + e.getEmail() + "</td>"
                            + "<td align=\"center\">" + e.getAge() + "</td>"
                            + "</tr>");
                });

                resultTable.append("</tbody>" +
                        "<tfoot>" +
                        "<tr>" +
                        "<th colspan=\"9\">Информация обо всех работниках по вашему запросу</th>" +
                        "</tr>" +
                        "</tfoot>" +
                        "</table>");
            } else {
                resultTable.append("<p style=\"color: red;\">Поиск не дал результатов</p>");
            }

            System.out.println("data NOT from CACHE");
            req.setAttribute(fullParamRequest.toString(), resultTable.toString());
        }

        req.setAttribute("resultTable", resultTable.toString());
        req.getRequestDispatcher("/jsp/result-of-search.jsp").forward(req, resp);

        context.complete();
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
