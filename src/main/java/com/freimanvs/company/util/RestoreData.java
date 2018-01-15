package com.freimanvs.company.util;

import com.freimanvs.company.entities.Employee;
import com.freimanvs.company.entities.Position;
import com.freimanvs.company.entities.Role;
import com.freimanvs.company.service.EmployeeService;
import com.freimanvs.company.service.PositionService;
import com.freimanvs.company.service.RoleService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class RestoreData {
    private static final String URL = "jdbc:mysql://localhost/company?useSSL=false";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "pass";
    private static final File FILESQL = new File("./sql.txt");
    private static final File TABLES = new File("./pre_sql.txt");
    private static final File FILESQL_JPA = new File("./jpa.txt");
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public static void restoreJDBC() {
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             Statement statement = connection.createStatement();
             Scanner data = new Scanner(FILESQL).useDelimiter(";[\\s]*");
             Scanner tables = new Scanner(TABLES).useDelimiter(";[\\s]*")) {

            while (tables.hasNext()) {
                String sql = tables.next();
                System.out.println(sql);
                statement.executeUpdate(sql);
            }

            while (data.hasNext()) {
                String sql = data.next();
                System.out.println(sql);
                statement.executeUpdate(sql);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void restoreJPA() {
        System.out.println("FILLING DATABASE WITH DATA FROM pre_sql.txt," +
                "sql.txt and jpa.txt files USING JDBC AND JPA...\n");
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             Statement statement = connection.createStatement();
             Scanner data = new Scanner(FILESQL_JPA).useDelimiter("[\\s]+");
             Scanner tables = new Scanner(TABLES).useDelimiter(";[\\s]*")) {

            System.out.println("CREATING TABLES BY JDPC...\n");
            while (tables.hasNext()) {
                String sql = tables.next();
                System.out.println(sql);
                statement.executeUpdate(sql);
            }
            System.out.println("========== THE TABLES ARE CREATED SUCCESSFULLY! ============\r\n");


            Session session = sessionFactory.openSession();
            EmployeeService employeeService = new EmployeeService(session);
            RoleService roleService = new RoleService(session);
            PositionService positionService = new PositionService(session);

            System.out.println("\r\nFILLING TABLES WITH DATA BY JPA...\r\n");
            while (data.hasNext()) {
                String entity = data.next();
                if (entity.equals("Employee")) {
                    String login = data.next();
                    String password = data.next();
                    String fio = data.next();
                    String department = data.next();
                    String city = data.next();
                    double salary = data.nextDouble();
                    String phoneNumber = data.next();
                    String email = data.next();
                    Employee employee = new Employee(login, password, fio, department,
                            city, salary, phoneNumber, email);
                    long id = employeeService.add(employee);
                    System.out.println(entity + " by id " + id + " has been added");
                } else if (entity.equals("Role")) {
                    String name = data.next();
                    Role role = new Role(name);
                    long id = roleService.add(role);
                    System.out.println(entity + " by id " + id + " has been added");
                } else if (entity.equals("Position")) {
                    String name = data.next();
                    Position position = new Position(name);
                    long id = positionService.add(position);
                    System.out.println(entity + " by id " + id + " has been added");
                } else {
                    System.out.println("A wrong entity: " + entity);
                    break;
                }
            }
            System.out.println("========== TABLES ARE FILLED SUCCESSFULLY! ============\r\n");
            System.out.println("THE SCHEMA 'company' IS READY TO USE!\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        restoreJPA();
    }
}
