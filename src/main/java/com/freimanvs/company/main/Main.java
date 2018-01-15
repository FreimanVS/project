package com.freimanvs.company.main;

import com.freimanvs.company.entities.Employee;
import com.freimanvs.company.entities.Position;
import com.freimanvs.company.service.EmployeeService;
import com.freimanvs.company.service.PositionService;
import com.freimanvs.company.util.HibernateUtil;
import com.freimanvs.company.util.RestoreData;
import com.freimanvs.company.util.WorkingWithFiles;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.File;
import java.sql.*;
import java.util.Set;

public class Main {
    private static final SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();
    private static Session session = SESSION_FACTORY.openSession();
    private static EmployeeService employeeService = new EmployeeService(session);
    private static PositionService positionService = new PositionService(session);
    private static final File OUTPUT_FILE = new File("./maxSalary.txt");
    private static final File OUTPUT_RESULT = new File("./result.txt");

    public static void main(String[] args) {

        System.out.println("============================");
        System.out.println("FILLING DATABASE WITH DATA FROM pre_sql.txt, sql.txt and jpa.txt files " +
                "USING JDBC AND JPA");
        System.out.println("==============================");
        RestoreData.restoreJPA();
        System.out.println("The database is ready!");
        try {

            //Создать хранимую функцию (средствами JDBC или непосредственно в БД), возвращающую
            //фамилия сотрудника, имеющего наибольшую зарплату

            System.out.println("====================== WORK WITH PROCEDURES =================");
            String dbURL = "jdbc:mysql://localhost:3306/company?useSSL=false";
            String user = "root";
            String password = "pass";

            try (Connection conn = DriverManager.getConnection(dbURL, user, password);
                 Statement createStatement = conn.createStatement();
                 CallableStatement callStatement = conn.prepareCall("{call newProcedure(?)}")) {

                System.out.println("Creating a new procedure...");
                String queryDrop = "DROP PROCEDURE IF EXISTS newProcedure";

                String queryCreate = "CREATE PROCEDURE newProcedure (OUT result VARCHAR(100))";
                queryCreate += "BEGIN ";
                queryCreate += "SELECT fio FROM employee\n" +
                        "WHERE salary = (SELECT MAX(salary) FROM employee)\n" +
                        "INTO result;";
                queryCreate += "END";

                createStatement.execute(queryDrop);
                createStatement.execute(queryCreate);
                createStatement.close();
                System.out.println("A procedure created successfully!");

                System.out.println("Calling the new procedure...");
                callStatement.registerOutParameter(1, Types.VARCHAR);

                callStatement.execute();

                String result = callStatement.getString(1);

                System.out.println("Done!");
//                System.out.println(result + " HAS THE BIGGEST SALARY");
                WorkingWithFiles.writeToFile(result, OUTPUT_FILE);
                System.out.println("YOU CAN SEE THE GUY WITH THE BIGGEST SALARY IN " + OUTPUT_FILE.getName());

                callStatement.close();


            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            //После успешного сохранения данных в БД необходимо вывести в консоль/ответ сервлета (или
            //внешний файл result.txt - опционально) информацию с сотрудниками, отсортированную по ИД
            //сотрудника по убыванию.

            System.out.println();
            System.out.println("========= GET ALL USERS DESC BY ID ============");
            Transaction transaction = session.beginTransaction();

            String hql = "FROM Employee E ORDER BY E.id DESC";
            Query<Employee> query = session.createQuery(hql, Employee.class);
            StringBuilder sb = new StringBuilder();
            query.stream().peek(q -> sb.append(q + "\r\n")).forEach(System.out::println);

            transaction.commit();

            WorkingWithFiles.writeToFile(sb.toString(), OUTPUT_RESULT);
            System.out.println("You can also see the users in the result.txt file");

            // Также потребуется произвести изменение 2 строк данной таблицы,
            //подменив фамилию сотрудника, а также его должность

            System.out.println();
            System.out.println("========== id 1 AND id 2 USERS' LASTNAME AND POSITION HAVE BEEN CHANGED ============");
            System.out.println("THERE WAS: ");
            employeeService.getList().forEach(System.out::println);

            Position position1 = positionService.getById(4L);
            Position position2 = positionService.getById(3L);

            Employee employee1 = employeeService.getById(1L);
            Employee employee2 = employeeService.getById(2L);

            employee1.setFio("Gerasimov_A.V.");
            employee2.setFio("Aleksandrov_I.U.");

            Set<Position> positions1 = employee1.getPositions();
            positions1.clear();
            positions1.add(position2);

            Set<Position> positions2 = employee2.getPositions();
            positions2.clear();
            positions2.add(position1);

            System.out.println("\r\nBECAME: ");
            employeeService.getList().forEach(System.out::println);


            //Удалить произвольные 3 строки из данной таблицы.
            System.out.println();
            System.out.println("=========== ALSO THREE ROWS HAVE BEEN DELETED ================");

            employeeService.deleteById(3L);
            employeeService.deleteById(2L);
            employeeService.deleteById(4L);

            employeeService.getList().forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            SESSION_FACTORY.close();
        }
    }
}
