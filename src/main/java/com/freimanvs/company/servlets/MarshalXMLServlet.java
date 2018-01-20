package com.freimanvs.company.servlets;

import com.freimanvs.company.entities.Company;
import com.freimanvs.company.entities.Employee;
import com.freimanvs.company.service.EmployeeService;
import com.freimanvs.company.util.FileManager;
import com.freimanvs.company.util.HibernateUtil;
import com.freimanvs.company.util.RestoreData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.IntStream;

@WebServlet("/MarshalXMLServlet")
public class MarshalXMLServlet extends HttpServlet {
    private static final String XMLPATH = "employee.xml";
    private static final String JSONPATH = "employee.json";
    private static final Jsonb JSONB = JsonbBuilder.create();
    private static final SessionFactory FACTORY = HibernateUtil.getSessionFactory();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (Session SESSION = FACTORY.openSession();
             PrintWriter pw = resp.getWriter()) {

            //initialization
            RestoreData.restoreJPA();

            //Первый этап - создание XML.
            //Используя сущность Employee (Сотрудники) из ДЗ2 и возможности, предоставляемые
            //технологией JAXB, необходимо произвести процедуру маршалинга состояния объектов данного
            //класса во внешний файл employee.xml. Для этих целей
            //рекомендую использовать отдельный сервлет MarshalXMLServlet

            EmployeeService employeeService = new EmployeeService(SESSION);
            List<Employee> employees = employeeService.getList();

            Company company = new Company();
            company.setEmployee(employees);
            FileManager.objectToXml(company, XMLPATH);

            pw.printf("Employee objects have been marshalled to a file '%s' succefully!\r\n", XMLPATH);

            //Второй пункт - поиск XPath.
            //Считав содержимое xml-документа employee.xml в
            //объект org.w3c.dom.Document или org.xml.sax.InputSource, необходимо найти все xml-теги
            //сотрудников, у которых зарплата превышает среднее значение. Допустимо логику описать в том
            //же сервлете MarshalXMLServlet либо создать запускаемый класс.

            XPathFactory factory = XPathFactory.newInstance();
            Object result = factory.newXPath().compile("//salary/text()")
                    .evaluate(new InputSource(new FileReader(XMLPATH)),
                            XPathConstants.NODESET);

            final NodeList nodes = (NodeList) result;

            Double avg = IntStream.range(0, nodes.getLength())
                    .mapToDouble((i -> Double.parseDouble(nodes.item(i).getNodeValue()))).average().orElse(0.0);
            pw.println("\r\n===============================");
            pw.printf("Average salary is %s\r\n", avg);

            result = factory.newXPath().compile("//salary[text()>" + avg + "]/text()")
                    .evaluate(new InputSource(new FileReader(XMLPATH)),
                            XPathConstants.NODESET);

            NodeList nodes2 = (NodeList) result;
            pw.printf("There are %d employees with a salary more than average." +
                    "\r\nThose values:\r\n", nodes2.getLength());

            IntStream.range(0, nodes2.getLength()).forEach(i ->
                    pw.println(nodes2.item(i).getParentNode().getParentNode().getChildNodes().item(7)
                            .getTextContent() + " : " + nodes2.item(i).getNodeValue()));

            //Третий пункт - конвертация XML в JSON.
            //Используя возможности библиотеки JSON или любой другой,
            //преобразовать содержимое файла employee.xml в JSON-формат.
            //Результат вывести в качестве ответа сервлета, одновременно сохранив в виде внешнего
            //файла employee.json.
            String resultJSON = FileManager.xmlToJSON(XMLPATH, Company.class);
            FileManager.writeToFile(resultJSON, JSONPATH);
            pw.println("\r\n====================================================================");
            pw.printf("JSON is in the file '%s' and printed here \r\n%s\r\n", JSONPATH, resultJSON);


            //Четвертый пункт - отображение внешнего JSON-файла на объектную модель.
            //Используя содержимое employee.json и возможности технологии JSON-B, получить список
            //объектов Employee и вывести в сервлете или запускаемом классе информацию о сотрудниках с
            //нечетными индексами в списке.

            pw.println("\r\n====================================================================");
            pw.println("Employees with odd indexes: 1, 3, 5, etc...");
            List<Employee> empls = JSONB.fromJson(new FileReader(JSONPATH), Company.class).getEmployee();
            IntStream.range(0, empls.size()).filter(i -> i % 2 == 0).forEach(i -> pw.println(empls.get(i)));
        } catch (FileNotFoundException | XPathExpressionException e) {
            e.printStackTrace();
        }
    }
}
