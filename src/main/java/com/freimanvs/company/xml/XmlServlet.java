package com.freimanvs.company.xml;

import com.freimanvs.company.entities.Employee;
import com.freimanvs.resources.XmlToObjBean;
import ru.otus.resource.XmlBean;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "/XMLServlet", urlPatterns = "/xml")
public class XmlServlet extends HttpServlet {

    @Resource(name="bean/XmlBeanFactory")
    private XmlBean bean;

    @Resource(name="bean/XmlToObjFactory")
    private XmlToObjBean bean2;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(PrintWriter writer = resp.getWriter()) {

            Employee employee = new Employee();
            employee.setId(12321);
            employee.setLogin("login");
            employee.setPassword("password");
            employee.setFio("Ivanov Ivan Ivanovich");
            employee.setDepartment("HR");
            employee.setCity("Moscow");
            employee.setSalary(100000L);
            employee.setPhoneNumber("+71234567890");
            employee.setEmail("loginHR@post.com");

//            Context context = new InitialContext();
//            XmlBean bean2 = (XmlBean) context.lookup("java:/comp/env/bean/XmlBeanFactory");

            bean.process(employee);
            writer.println("xml path = $CATALINA_BASE" + bean.getXmlPath().toString().substring(2));
            writer.println(bean2.unmarshal(Employee.class));
        }
    }
}
