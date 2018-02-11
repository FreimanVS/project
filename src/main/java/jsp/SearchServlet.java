package jsp;

import com.freimanvs.company.entities.Employee;
import com.freimanvs.company.service.EmployeeService;
import com.freimanvs.company.service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/search"}, name = "searchServlet")
public class SearchServlet extends HttpServlet {

    Service<Employee> employeeService = new EmployeeService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
            String dep = req.getParameter("department");
            String city = req.getParameter("city");
            String ageFromString = req.getParameter("ageFrom");
            String ageToString = req.getParameter("ageTo");
            int ageFrom = ageFromString.equals("") ? 1 : Integer.parseInt(ageFromString);
            int ageTo = ageToString.equals("") ? Integer.MAX_VALUE : Integer.parseInt(ageToString);

            List<Employee> list = employeeService.getList().stream().filter(e -> e.getLogin().contains(login)
                    && e.getFio().contains(fio) && e.getDepartment().contains(dep) && e.getCity().contains(city)
                    &&  e.getAge() >= ageFrom && e.getAge() <= ageTo)
                    .collect(Collectors.toList());


            if (list != null && !list.isEmpty()) {
                resultTable.append("<table><caption><b>РАБОТНИКИ</b></caption>" +
                        "<thead><tr>" +
                        "<th>id</th>" +
                        "<th>Логин</th>" +
                        "<th>ФИО</th>" +
                        "<th>Должность</th>" +
                        "<th>Город</th>" +
                        "<th>Зарплата</th>" +
                        "<th>Номер телефона</th>" +
                        "<th>email</th>" +
                        "<th>Возраст</th>" +
                        "</tr></thead><tbody>");

                list.forEach(e -> resultTable.append("<tr>"
                        + "<td align=\"center\">"+ e.getId() + "</td>"
                        + "<td align=\"center\">" + e.getLogin() + "</td>"
                        + "<td align=\"center\">" + e.getFio() + "</td>"
                        + "<td align=\"center\">" + e.getDepartment() + "</td>"
                        + "<td align=\"center\">" + e.getCity() + "</td>"
                        + "<td align=\"center\">" + e.getSalary() + "</td>"
                        + "<td align=\"center\">" + e.getPhoneNumber() + "</td>"
                        + "<td align=\"center\">" + e.getEmail() + "</td>"
                        + "<td align=\"center\">" + e.getAge() + "</td>"
                        + "</tr>"));

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
    }
}
