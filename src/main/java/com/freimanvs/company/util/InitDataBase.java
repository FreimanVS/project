package com.freimanvs.company.util;

import com.freimanvs.company.entities.Company;
import com.freimanvs.company.entities.Employee;
import com.freimanvs.company.entities.Position;
import com.freimanvs.company.entities.Role;
import com.freimanvs.company.interceptors.InitDbInterceptor;
import com.freimanvs.company.interceptors.bindings.Logging;
import com.freimanvs.company.service.interfaces.EmployeeServicePersInterface;
import com.freimanvs.company.service.interfaces.PositionServicePersInterface;
import com.freimanvs.company.service.interfaces.RoleServicePersInterface;
import com.freimanvs.company.util.interfaces.DbXMLBean;
import com.freimanvs.company.util.interfaces.FileManagerBean;

import javax.ejb.*;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.nio.file.Path;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@Logging
public class InitDataBase implements DbXMLBean {

//    private static final Path XMLPATH = Paths.get("./company.xml");

    @PersistenceContext(unitName = "mysqlejb")
    private EntityManager em;

//    @EJB
    @Inject
    private FileManagerBean fileManagerBean;

//    @EJB
    @Inject
//    @EmployeeService(value = EmployeeServiceEnum.PERS)
    private EmployeeServicePersInterface employeeService;

//    @EJB
    @Inject
    private RoleServicePersInterface roleService;

//    @EJB
    @Inject
    private PositionServicePersInterface positionService;

    @Interceptors(InitDbInterceptor.class)
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void xmlToDB(Path xmlpath) {
        //XML to DB
        Company company = (Company) fileManagerBean.xmlToObj(xmlpath, Company.class);

        List<Employee> employees = company.getEmployee();
        List<Role> roles = company.getRoles();
        List<Position> positions = company.getPositions();

        createTables();
        createProcedures();

        roles.forEach(em::merge);
        positions.forEach(em::merge);
        employees.forEach(em::merge);
    }

    @Interceptors(InitDbInterceptor.class)
    public void dbToXml(Path xmlpath) {
        save(xmlpath);
//        deleteAll();
        clearSchema();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    private void createTables() {
        em.createNativeQuery("CREATE SCHEMA IF NOT EXISTS company;").executeUpdate();
        em.createNativeQuery("USE company;").executeUpdate();

        em.createNativeQuery("CREATE TABLE IF NOT EXISTS company.user (\n" +
                "login VARCHAR(100) NOT NULL PRIMARY KEY,\n" +
                "password VARCHAR(100) NOT NULL\n" +
                ");").executeUpdate();

        em.createNativeQuery("CREATE TABLE IF NOT EXISTS company.employee (\n" +
                "id BIGINT NOT NULL AUTO_INCREMENT,\n" +
                "login VARCHAR(100) NOT NULL,\n" +
                "fio VARCHAR(100) NOT NULL,\n" +
                "department VARCHAR(100) NOT NULL,\n" +
                "city VARCHAR(100) NOT NULL,\n" +
                "salary DOUBLE NOT NULL,\n" +
                "phoneNumber VARCHAR(50) NOT NULL,\n" +
                "email VARCHAR(100) NOT NULL,\n" +
                "age INTEGER NOT NULL,\n" +
                "PRIMARY KEY (id),\n" +
                "CONSTRAINT uniqEmployee UNIQUE (login),\n" +
                "CONSTRAINT fk_employee_login_user FOREIGN KEY (login) REFERENCES user(login)\n" +
                ");").executeUpdate();

        em.createNativeQuery("CREATE TABLE IF NOT EXISTS company.role (\n" +
                    "                id BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    "                name VARCHAR(100) NOT NULL,\n" +
                    "                PRIMARY KEY (id),\n" +
                    "                CONSTRAINT uniqRole UNIQUE (name)\n" +
                    "        );").executeUpdate();

        em.createNativeQuery("CREATE TABLE IF NOT EXISTS company.employee_role (\n" +
                    "                employeeId BIGINT NOT NULL,\n" +
                    "                roleId BIGINT NOT NULL,\n" +
                    "                PRIMARY KEY (employeeId, roleId),\n" +
                    "                CONSTRAINT fk_employee_role_employeeId FOREIGN KEY (employeeId) REFERENCES employee(id),\n" +
                    "                CONSTRAINT fk_employee_role_roleId FOREIGN KEY (roleId) REFERENCES role(id)\n" +
                    "        );").executeUpdate();

        em.createNativeQuery("CREATE TABLE IF NOT EXISTS company.position (\n" +
                    "                id BIGINT NOT NULL AUTO_INCREMENT,\n" +
                    "                name VARCHAR(100) NOT NULL,\n" +
                    "                PRIMARY KEY (id),\n" +
                    "                CONSTRAINT uniqPosition UNIQUE (name)\n" +
                    "        );").executeUpdate();

        em.createNativeQuery("CREATE TABLE IF NOT EXISTS company.employee_position (\n" +
                    "                employeeId BIGINT NOT NULL,\n" +
                    "                positionId BIGINT NOT NULL,\n" +
                    "                PRIMARY KEY (employeeId, positionId),\n" +
                    "                CONSTRAINT fk_employee_position_employeeId FOREIGN KEY (employeeId) REFERENCES employee(id),\n" +
                    "                CONSTRAINT fk_employee_position_positionId FOREIGN KEY (positionId) REFERENCES company.position(id)\n" +
                    "        );").executeUpdate();

        em.createNativeQuery("CREATE TABLE IF NOT EXISTS company.analytics (\n" +
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

        em.createNativeQuery("CREATE TABLE IF NOT EXISTS company.performance (\n" +
                "                id BIGINT NOT NULL AUTO_INCREMENT,\n" +
                "                name VARCHAR(500) NULL,\n" +
                "                ms BIGINT NULL,\n" +
                "                PRIMARY KEY (id));").executeUpdate();
    }

    private void save(Path xmlpath) {
        //DB to XML

        List<Employee> employees2 = employeeService.getList();
        List<Role> roles2 = roleService.getList();
        List<Position> positions2 = positionService.getList();

        Company company = new Company();
        company.setEmployee(employees2);
        company.setRoles(roles2);
        company.setPositions(positions2);

        fileManagerBean.objectToXml(company, xmlpath);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    private void createProcedures() {
            String queryDrop = "DROP procedure IF EXISTS company.with_max_salary;";
            String queryCreate = "CREATE PROCEDURE company.with_max_salary()" +
                    "BEGIN " +
                    "SELECT * FROM company.employee " +
                    "WHERE salary = (SELECT MAX(salary) FROM company.employee);" +
                    "END";
        em.createNativeQuery(queryDrop).executeUpdate();
        em.createNativeQuery(queryCreate).executeUpdate();

            queryDrop = "DROP procedure IF EXISTS company.avg_salary;";
            queryCreate = "CREATE PROCEDURE company.avg_salary()" +
                    "BEGIN " +
                    "SELECT AVG(salary) FROM company.employee;" +
                    "END";
        em.createNativeQuery(queryDrop).executeUpdate();
        em.createNativeQuery(queryCreate).executeUpdate();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    private void clearSchema() {
        em.createNativeQuery("DROP TABLE IF EXISTS company.employee_position;").executeUpdate();
        em.createNativeQuery("DROP TABLE IF EXISTS company.employee_role;").executeUpdate();
        em.createNativeQuery("DROP TABLE IF EXISTS company.employee;").executeUpdate();
        em.createNativeQuery("DROP TABLE IF EXISTS company.position;").executeUpdate();
        em.createNativeQuery("DROP TABLE IF EXISTS company.role;").executeUpdate();
        em.createNativeQuery("DROP TABLE IF EXISTS company.analytics;").executeUpdate();
        em.createNativeQuery("DROP TABLE IF EXISTS company.performance;").executeUpdate();
        em.createNativeQuery("DROP TABLE IF EXISTS company.user;").executeUpdate();

        em.createNativeQuery("DROP PROCEDURE IF EXISTS company.with_max_salary;").executeUpdate();
        em.createNativeQuery("DROP PROCEDURE IF EXISTS company.avg_salary;").executeUpdate();

    }
}
