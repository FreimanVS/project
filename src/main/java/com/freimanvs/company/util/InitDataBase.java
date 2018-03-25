package com.freimanvs.company.util;

import com.freimanvs.company.entities.Company;
import com.freimanvs.company.entities.Employee;
import com.freimanvs.company.entities.Position;
import com.freimanvs.company.entities.Role;
import com.freimanvs.company.service.EmployeeService;
import com.freimanvs.company.service.PositionService;
import com.freimanvs.company.service.RoleService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.nio.file.Path;
import java.util.List;

public class InitDataBase {

    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static Session session = getSession();
//    private static final Path XMLPATH = Paths.get("./company.xml");

    public static void xmlToDB(Path xmlpath) {
        init(xmlpath);
    }

    public static void dbToXml(Path xmlpath) {
        save(xmlpath);
        deleteAll();
        clearSchema();
    }

    private static void createTables() {

        Transaction transaction = session.beginTransaction();
        try {
            session.createNativeQuery("CREATE SCHEMA IF NOT EXISTS company;").executeUpdate();
            session.createNativeQuery("USE company;").executeUpdate();

            session.createNativeQuery("CREATE TABLE IF NOT EXISTS company.employee (\n" +
                    "                id BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    "                login VARCHAR(100) NOT NULL,\n" +
                    "                password VARCHAR(100) NOT NULL,\n" +
                    "                fio VARCHAR(100) NOT NULL,\n" +
                    "                department VARCHAR(100) NOT NULL,\n" +
                    "                city VARCHAR(100) NOT NULL,\n" +
                    "                salary DOUBLE NOT NULL,\n" +
                    "                phoneNumber VARCHAR(50) NOT NULL,\n" +
                    "                email VARCHAR(100) NOT NULL,\n" +
                    "                age INTEGER NOT NULL,\n" +
                    "                PRIMARY KEY (id),\n" +
                    "                CONSTRAINT uniqEmployee UNIQUE (login)\n" +
                    "        );").executeUpdate();

            session.createNativeQuery("CREATE TABLE IF NOT EXISTS company.role (\n" +
                    "                id BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    "                name VARCHAR(100) NOT NULL,\n" +
                    "                PRIMARY KEY (id),\n" +
                    "                CONSTRAINT uniqRole UNIQUE (name)\n" +
                    "        );").executeUpdate();

            session.createNativeQuery("CREATE TABLE IF NOT EXISTS company.employee_role (\n" +
                    "                employeeId BIGINT NOT NULL,\n" +
                    "                roleId BIGINT NOT NULL,\n" +
                    "                PRIMARY KEY (employeeId, roleId),\n" +
                    "                CONSTRAINT fk_employee_role_employeeId FOREIGN KEY (employeeId) REFERENCES employee(id),\n" +
                    "                CONSTRAINT fk_employee_role_roleId FOREIGN KEY (roleId) REFERENCES role(id)\n" +
                    "        );").executeUpdate();

            session.createNativeQuery("CREATE TABLE IF NOT EXISTS company.position (\n" +
                    "                id BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    "                name VARCHAR(100) NOT NULL,\n" +
                    "                PRIMARY KEY (id),\n" +
                    "                CONSTRAINT uniqPosition UNIQUE (name)\n" +
                    "        );").executeUpdate();

            session.createNativeQuery("CREATE TABLE IF NOT EXISTS company.employee_position (\n" +
                    "                employeeId BIGINT NOT NULL,\n" +
                    "                positionId BIGINT NOT NULL,\n" +
                    "                PRIMARY KEY (employeeId, positionId),\n" +
                    "                CONSTRAINT fk_employee_position_employeeId FOREIGN KEY (employeeId) REFERENCES employee(id),\n" +
                    "                CONSTRAINT fk_employee_position_positionId FOREIGN KEY (positionId) REFERENCES company.position(id)\n" +
                    "        );").executeUpdate();

            session.createNativeQuery("CREATE TABLE IF NOT EXISTS company.analytics (\n" +
                    "id BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    "marker_name VARCHAR(100) NULL,\n" +
                    "jsp_name VARCHAR(100) NULL,\n" +
                    "ip VARCHAR(100) NULL,\n" +
                    "browser_info VARCHAR(10000) NULL,\n" +
                    "client_time DATETIME NULL,\n" +
                    "server_time DATETIME NULL,\n" +
                    "login VARCHAR(100) NULL,\n" +
                    "cookie VARCHAR(10000) NULL,\n" +
                    "prev_id BIGINT NULL,\n" +
                    "PRIMARY KEY (id)\n" +
                    ");").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public static void init(Path xmlpath) {
        //XML to DB
        Company company = (Company) FileManager.xmlToObj(xmlpath, Company.class);

        List<Employee> employees = company.getEmployee();
        List<Role> roles = company.getRoles();
        List<Position> positions = company.getPositions();

        createTables();
        createProcedures();

        roles.forEach(InitDataBase::initRole);
        positions.forEach(InitDataBase::initPosition);
        employees.forEach(InitDataBase::initEmployee);
    }

    public static void save(Path xmlpath) {
        //DB to XML
        EmployeeService employeeService = new EmployeeService(session);
        RoleService roleService = new RoleService(session);
        PositionService positionService = new PositionService(session);


        List<Employee> employees2 = employeeService.getList();
        List<Role> roles2 = roleService.getList();
        List<Position> positions2 = positionService.getList();

        Company company = new Company();
        company.setEmployee(employees2);
        company.setRoles(roles2);
        company.setPositions(positions2);

        FileManager.objectToXml(company, xmlpath);
    }

    public static void close() {
        session.close();
    }

    private static Session getSession() {
        if (session == null) {
            session = sessionFactory.openSession();
        }
        return session;
    }

    private static void initRole(Role role) {
        Transaction transaction = session.beginTransaction();
        try {
            session.save(role);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    private static void initPosition(Position position) {
        Transaction transaction = session.beginTransaction();
        try {
            session.save(position);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    private static void initEmployee(Employee employee) {
        Transaction transaction = session.beginTransaction();
        try {
            session.save(employee);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    private static void createProcedures() {
        Transaction transaction = session.beginTransaction();
        try {
            String queryDrop = "DROP procedure IF EXISTS company.with_max_salary;";
            String queryCreate = "CREATE PROCEDURE company.with_max_salary()" +
                    "BEGIN " +
                    "SELECT * FROM company.employee " +
                    "WHERE salary = (SELECT MAX(salary) FROM company.employee);" +
                    "END";
            session.createNativeQuery(queryDrop).executeUpdate();
            session.createNativeQuery(queryCreate).executeUpdate();

            queryDrop = "DROP procedure IF EXISTS company.avg_salary;";
            queryCreate = "CREATE PROCEDURE company.avg_salary()" +
                    "BEGIN " +
                    "SELECT AVG(salary) FROM company.employee;" +
                    "END";
            session.createNativeQuery(queryDrop).executeUpdate();
            session.createNativeQuery(queryCreate).executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    private static void clearSchema() {
        Transaction transaction = session.beginTransaction();
        try {
            session.createNativeQuery("DROP TABLE IF EXISTS company.employee_position;").executeUpdate();
            session.createNativeQuery("DROP TABLE IF EXISTS company.employee_role;").executeUpdate();
            session.createNativeQuery("DROP TABLE IF EXISTS company.employee;").executeUpdate();
            session.createNativeQuery("DROP TABLE IF EXISTS company.position;").executeUpdate();
            session.createNativeQuery("DROP TABLE IF EXISTS company.role;").executeUpdate();
            session.createNativeQuery("DROP TABLE IF EXISTS company.analytics;").executeUpdate();

            session.createNativeQuery("DROP PROCEDURE IF EXISTS company.with_max_salary;").executeUpdate();
            session.createNativeQuery("DROP PROCEDURE IF EXISTS company.avg_salary;").executeUpdate();

            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public static void deleteAll() {
        removeEmployee();
        removeRole();
        removePosition();
    }

    private static void removeRole() {
        RoleService roleService = new RoleService(session);
        roleService.getList().forEach(r -> roleService.deleteById(r.getId()));
    }

    private static void removePosition() {
        PositionService positionService = new PositionService(session);
        positionService.getList().forEach(p -> positionService.deleteById(p.getId()));
    }

    private static void removeEmployee() {
        EmployeeService employeeService = new EmployeeService(session);
        employeeService.getList().forEach(e -> employeeService.deleteById(e.getId()));
    }
}
