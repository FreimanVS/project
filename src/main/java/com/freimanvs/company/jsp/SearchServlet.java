package com.freimanvs.company.jsp;

import com.freimanvs.company.dao.EmployeeDAO;
import com.freimanvs.company.entities.Employee;
import com.freimanvs.company.service.EmployeeService;
import com.freimanvs.company.service.Service;
import com.freimanvs.company.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/search"}, name = "searchServlet", asyncSupported = true)
public class SearchServlet extends HttpServlet {

//    Service<Employee> employeeService = new EmployeeService();

    private ScheduledExecutorService scheduledExecutorService;

    @Override
    public void init() throws ServletException {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
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

    private void search(final AsyncContext context) throws ServletException, IOException {

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

//            List<Employee> list = employeeService.getList().stream().filter(e -> e.getLogin().contains(login)
//                    && e.getFio().contains(fio)
//                    && e.getPositions().stream().anyMatch(position -> position.getName().contains(pos))
//                    && e.getCity().contains(city)
//                    && e.getAge() >= ageFrom && e.getAge() <= ageTo)
//                    .collect(Collectors.toList());

            List<Employee> list = getBy(login, fio, pos, city, ageFrom, ageTo);

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

    private List<Employee> getBy(String login, String fio, String position, String city,
                                 int ageFrom, int ageTo) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Employee> list = null;
        Transaction transaction = session.beginTransaction();
        try {
            Query<Employee> query = session.createQuery("select DISTINCT e from Employee e join e.positions p"
                    +" WHERE e.login LIKE :login"
                    + " AND e.fio LIKE :fio"
                    + " AND p.name LIKE :positions"
                    + " AND e.city LIKE :city"
                    + " AND e.age >= :ageFrom"
                    + " AND e.age <= :ageTo")
                    .setParameter("login", "%"+login+"%")
                    .setParameter("fio", "%"+fio+"%")
                    .setParameter("positions", "%"+position+"%")
                    .setParameter("city", "%"+city+"%")
                    .setParameter("ageFrom", ageFrom)
                    .setParameter("ageTo", ageTo);
            list = query.list();

            if (list != null && !list.isEmpty()) {
                list.forEach(EmployeeDAO::initialize);
            }

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void destroy() {

        System.out.println("Closing scheduledExecutorService...");
        scheduledExecutorService.shutdownNow();
        System.out.println("ScheduledExecutorService is closed!");
//        try {
//            scheduledExecutorService.awaitTermination(10, TimeUnit.SECONDS);
//            System.out.println("ScheduledExecutorService is closed!");
//        } catch (InterruptedException e) {
//            System.out.println("ERROR: scheduledExecutorService was not closed!");
//            e.printStackTrace();
//        }
        super.destroy();
    }
}
