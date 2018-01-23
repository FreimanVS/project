package com.freimanvs.company.util;

import com.freimanvs.company.entities.Employee;
import com.freimanvs.company.entities.Position;
import com.freimanvs.company.entities.Role;
import com.freimanvs.company.service.EmployeeService;
import com.freimanvs.company.service.PositionService;
import com.freimanvs.company.service.RoleService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class RestoreData {
    private static final String URL = "jdbc:mysql://localhost/company?useSSL=false";
//    private static final String URL = "jdbc:h2:~/test";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "pass";
    private static final File FILESQL = new File("./sql.txt");
    private static final File TABLES = new File("./pre_sql.txt");
    private static final File FILESQL_JPA = new File("./jpa.txt");

    private static void initFiles() {
        FileManager.writeToFile("INSERT INTO company.employee(login, password, fio, department, city, salary, phoneNumber, email)\n" +
                "VALUES ('login', 'password', 'fio', 'department', 'city', 123.45, 'phoneNumber', 'email');\n" +
                "\n" +
                "INSERT INTO company.employee(login, password, fio, department, city, salary, phoneNumber, email)\n" +
                "VALUES ('login2', 'password2', 'fio2', 'department2', 'city2', 123.452, 'phoneNumber2', 'email2');\n" +
                "\n" +
                "INSERT INTO company.employee(login, password, fio, department, city, salary, phoneNumber, email)\n" +
                "VALUES ('login3', 'password2', 'fio3', 'department3', 'city3', 123.453, 'phoneNumber3', 'email3');\n" +
                "\n" +
                "INSERT INTO company.employee(login, password, fio, department, city, salary, phoneNumber, email)\n" +
                "VALUES ('login4', 'password4', 'fio4', 'department4', 'city4', 123.454, 'phoneNumber4', 'email4');\n" +
                "\n" +
                "INSERT INTO company.role(name) VALUES ('admin');\n" +
                "INSERT INTO company.role(name) VALUES ('user');\n" +
                "\n" +
                "INSERT INTO company.employee_role(employeeId, roleId) VALUES (1, 1);\n" +
                "INSERT INTO company.employee_role(employeeId, roleId) VALUES (2, 2);\n" +
                "INSERT INTO company.employee_role(employeeId, roleId) VALUES (3, 1);\n" +
                "\n" +
                "INSERT INTO company.position(name) VALUES ('position');\n" +
                "INSERT INTO company.position(name) VALUES ('position2');\n" +
                "\n" +
                "INSERT INTO company.employee_position(employeeId, positionId) VALUES (1, 1);\n" +
                "INSERT INTO company.employee_position(employeeId, positionId) VALUES (2, 2);\n" +
                "INSERT INTO company.employee_position(employeeId, positionId) VALUES (3, 1);" ,FILESQL);

        FileManager.writeToFile(
                "CREATE SCHEMA IF NOT EXISTS company;\n" +
                "DROP TABLE IF EXISTS company.employee_position;\n" +
                "DROP TABLE IF EXISTS company.employee_role;\n" +
                "DROP TABLE IF EXISTS company.employee;\n" +
                "DROP TABLE IF EXISTS company.position;\n" +
                "DROP TABLE IF EXISTS company.role;\n" +
                "\n" +
                "CREATE TABLE IF NOT EXISTS company.employee (\n" +
                "id BIGINT NOT NULL AUTO_INCREMENT,\n" +
                "login VARCHAR(100) NOT NULL,\n" +
                "password VARCHAR(100) NOT NULL,\n" +
                "fio VARCHAR(100) NOT NULL,\n" +
                "department VARCHAR(100) NOT NULL,\n" +
                "city VARCHAR(100) NOT NULL,\n" +
                "salary DOUBLE NOT NULL,\n" +
                "phoneNumber VARCHAR(50) NOT NULL,\n" +
                "email VARCHAR(100) NOT NULL,\n" +
                "PRIMARY KEY (id),\n" +
                "CONSTRAINT uniqEmployee UNIQUE (login)\n" +
                ")" +
//                "\nENGINE = InnoDB\n" +
//                "DEFAULT CHARACTER SET = utf8\n" +
//                "COLLATE = utf8_general_ci" +
                ";\n" +
                "\n" +
                "CREATE TABLE IF NOT EXISTS company.role (\n" +
                "id BIGINT NOT NULL AUTO_INCREMENT,\n" +
                "name VARCHAR(100) NOT NULL,\n" +
                "PRIMARY KEY (id),\n" +
                "CONSTRAINT uniqRole UNIQUE (name)\n" +
                ")" +
//                "\nENGINE = InnoDB\n" +
//                "DEFAULT CHARACTER SET = utf8\n" +
//                "COLLATE = utf8_general_ci" +
                ";\n" +
                "\n" +
                "CREATE TABLE IF NOT EXISTS company.employee_role (\n" +
                "employeeId BIGINT NOT NULL,\n" +
                "roleId BIGINT NOT NULL,\n" +
                "PRIMARY KEY (employeeId, roleId),\n" +
                "CONSTRAINT fk_employee_role_employeeId FOREIGN KEY (employeeId) REFERENCES employee(id),\n" +
                "CONSTRAINT fk_employee_role_roleId FOREIGN KEY (roleId) REFERENCES role(id)\n" +
                ")" +
//                "\nENGINE = InnoDB\n" +
//                "DEFAULT CHARACTER SET = utf8\n" +
//                "COLLATE = utf8_general_ci" +
                ";\n" +
                "\n" +
                "CREATE TABLE IF NOT EXISTS company.position (\n" +
                "id BIGINT NOT NULL AUTO_INCREMENT,\n" +
                "name VARCHAR(100) NOT NULL,\n" +
                "PRIMARY KEY (id),\n" +
                "CONSTRAINT uniqPosition UNIQUE (name)\n" +
                ")" +
//                "\nENGINE = InnoDB\n" +
//                "DEFAULT CHARACTER SET = utf8\n" +
//                "COLLATE = utf8_general_ci" +
                ";\n" +
                "\n" +
                "CREATE TABLE IF NOT EXISTS company.employee_position (\n" +
                "employeeId BIGINT NOT NULL,\n" +
                "positionId BIGINT NOT NULL,\n" +
                "PRIMARY KEY (employeeId, positionId),\n" +
                "CONSTRAINT fk_employee_position_employeeId FOREIGN KEY (employeeId) REFERENCES employee(id),\n" +
                "CONSTRAINT fk_employee_position_positionId FOREIGN KEY (positionId) REFERENCES company.position(id)\n" +
                ")" +
//                "\nENGINE = InnoDB\n" +
//                "DEFAULT CHARACTER SET = utf8\n" +
//                "COLLATE = utf8_general_ci" +
                ";", TABLES);
        FileManager.writeToFile("Role user\n" +
                "Role admin\n" +
                "Position worker\n" +
                "Position HR\n" +
                "Position Accounter\n" +
                "Position Director\n" +
                "Employee login1 password1 Petrosyan_A.A. Human_Resources Moscow 123,05 +71234567890 login1@company.com\n" +
                "Employee login2 password2 Smirnova_B.C. Marketing Saint-Petersburg 342,5 +7192348128 login2@company.com\n" +
                "Employee login3 password3 Evegniev_H.E. Research_and_Development Ekaterinburg 34147,0 +4872488234 login3@company.com\n" +
                "Employee login4 password4 Durova_S.A. I.T. NizhniiNovgorod 183,25 +128347383 login4@company.com\n" +
                "Employee login5 password5 Druzhnikov_I.A. Maintenance Saratov 3434347,0 +98234783 login5@company.com\n" +
                "Employee login6 password6 Germanov_A.U. Sales Arhangelsk 342217,1 +823492374 login6@company.com\n" +
                "Employee login7 password7 Vasiliev_I.I. Customer_Service Astana 9535656,2 +342348382 login7@company.com\n" +
                "Employee login8 password8 Medkova_S.Z. Finance Minsk 323547,4 +23498239472 login8@company.com\n" +
                "Employee login9 password9 Andropov_E.V. Dispatch_Department Berlin 34897,5 +2934829347 login9@company.com\n" +
                "Employee login10 password10 Alferov_S.Z. Marketing New-York 34897,7 +237472341 login10@company.com", FILESQL_JPA);
    }

    public static void restoreJDBC() {
        initFiles();
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

            System.out.println("========== TABLES ARE FILLED SUCCESSFULLY! ============\r\n");
            System.out.println("THE SCHEMA 'company' IS READY TO USE!\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void restoreJPA() {
        initFiles();
        System.out.println("FILLING DATABASE WITH DATA FROM pre_sql.txt," +
                "sql.txt and jpa.txt files USING JDBC AND JPA...\n");

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
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
                switch (entity) {
                    case "Employee": {
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
                        break;
                    }
                    case "Role": {
                        String name = data.next();
                        Role role = new Role(name);
                        long id = roleService.add(role);
                        System.out.println(entity + " by id " + id + " has been added");
                        break;
                    }
                    case "Position": {
                        String name = data.next();
                        Position position = new Position(name);
                        long id = positionService.add(position);
                        System.out.println(entity + " by id " + id + " has been added");
                        break;
                    }
                    default:
                        System.out.println("A wrong entity: " + entity);
                        break;
                }
            }
            System.out.println("========== TABLES ARE FILLED SUCCESSFULLY! ============\r\n");
            System.out.println("THE SCHEMA 'company' IS READY TO USE!\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }

    public static void main(String[] args) {
        restoreJPA();
//        restoreJDBC();
    }
}
