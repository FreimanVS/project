package com.freimanvs.company.main;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

/**
 * Created by Brightsunrise on 04.12.2016.
 */

public class JDBC {
    private static final String URL = "jdbc:mysql://localhost/company?useSSL=false";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "pass";
    private static final File FILESQL = new File("./sql.txt");

    private void jdbc() {


        try {
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
        } catch (Exception e) {
            System.out.println("An error of a driver");
            return;
        }

        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD)) {
            try (Statement statement = connection.createStatement();
                 Scanner sc = new Scanner(FILESQL).useDelimiter(";[\\s]+")) {

                //CLEAR the SCHEMA company
                statement.executeUpdate("DROP TABLE IF EXISTS employee_position");
                statement.executeUpdate("DROP TABLE IF EXISTS employee_role");
                statement.executeUpdate("DROP TABLE IF EXISTS employee");
                statement.executeUpdate("DROP TABLE IF EXISTS position");
                statement.executeUpdate("DROP TABLE IF EXISTS role");

                //CREATE TABLES
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS company.employee (\n" +
                        "    \n" +
                        "id BIGINT NOT NULL AUTO_INCREMENT,\n" +
                        "    \n" +
                        "login VARCHAR(100) NOT NULL,\n" +
                        "\n" +
                        "password VARCHAR(100) NOT NULL,\n" +
                        "\n" +
                        "fio VARCHAR(100) NOT NULL,\n" +
                        "\n" +
                        "department VARCHAR(100) NOT NULL,\n" +
                        "\n" +
                        "city VARCHAR(100) NOT NULL,\n" +
                        "\n" +
                        "salary DOUBLE NOT NULL,\n" +
                        "\n" +
                        "phoneNumber VARCHAR(50) NOT NULL,\n" +
                        "\n" +
                        "email VARCHAR(100) NOT NULL,\n" +
                        "  \n" +
                        "PRIMARY KEY (id),\n" +
                        "\n" +
                        "CONSTRAINT uniqEmployee UNIQUE (login)\n" +
                        ")\n" +
                        "\n" +
                        "ENGINE = InnoDB\n" +
                        "\n" +
                        "DEFAULT CHARACTER SET = utf8\n" +
                        "\n" +
                        "COLLATE = utf8_general_ci;");

                statement.executeUpdate("CREATE TABLE IF NOT EXISTS company.role (\n" +
                        "    \n" +
                        "id BIGINT NOT NULL AUTO_INCREMENT,\n" +
                        "    \n" +
                        "name VARCHAR(100) NOT NULL,\n" +
                        "  \n" +
                        "PRIMARY KEY (id),\n" +
                        "\n" +
                        "CONSTRAINT uniqRole UNIQUE (name)\n" +
                        ")\n" +
                        "\n" +
                        "ENGINE = InnoDB\n" +
                        "\n" +
                        "DEFAULT CHARACTER SET = utf8\n" +
                        "\n" +
                        "COLLATE = utf8_general_ci;");

                statement.executeUpdate("CREATE TABLE IF NOT EXISTS company.employee_role (\n" +
                        "    \n" +
                        "employeeId BIGINT NOT NULL,\n" +
                        "\n" +
                        "roleId BIGINT NOT NULL,\n" +
                        "  \n" +
                        "PRIMARY KEY (employeeId, roleId),\n" +
                        "\n" +
                        "CONSTRAINT fk_employee_role_employeeId FOREIGN KEY (employeeId) REFERENCES employee(id),\n" +
                        "\n" +
                        "CONSTRAINT fk_employee_role_roleId FOREIGN KEY (roleId) REFERENCES role(id)\n" +
                        ")\n" +
                        "\n" +
                        "ENGINE = InnoDB\n" +
                        "\n" +
                        "DEFAULT CHARACTER SET = utf8\n" +
                        "\n" +
                        "COLLATE = utf8_general_ci;");

                statement.executeUpdate("CREATE TABLE IF NOT EXISTS company.position (\n" +
                        "    \n" +
                        "id BIGINT NOT NULL AUTO_INCREMENT,\n" +
                        "\n" +
                        "name VARCHAR(100) NOT NULL,\n" +
                        "  \n" +
                        "PRIMARY KEY (id),\n" +
                        "\n" +
                        "CONSTRAINT uniqPosition UNIQUE (name)\n" +
                        ")\n" +
                        "\n" +
                        "ENGINE = InnoDB\n" +
                        "\n" +
                        "DEFAULT CHARACTER SET = utf8\n" +
                        "\n" +
                        "COLLATE = utf8_general_ci;");

                statement.executeUpdate("CREATE TABLE IF NOT EXISTS company.employee_position (\n" +
                        "    \n" +
                        "employeeId BIGINT NOT NULL,\n" +
                        "\n" +
                        "positionId BIGINT NOT NULL,\n" +
                        "  \n" +
                        "PRIMARY KEY (employeeId, positionId),\n" +
                        "\n" +
                        "CONSTRAINT fk_employee_position_employeeId FOREIGN KEY (employeeId) REFERENCES employee(id),\n" +
                        "\n" +
                        "CONSTRAINT fk_employee_position_positionId FOREIGN KEY (positionId) REFERENCES company.position(id)\n" +
                        ")\n" +
                        "\n" +
                        "ENGINE = InnoDB\n" +
                        "\n" +
                        "DEFAULT CHARACTER SET = utf8\n" +
                        "\n" +
                        "COLLATE = utf8_general_ci;");

                //INSERT DATA
                while (sc.hasNext()) {
                    String sql = sc.next();
                    statement.executeUpdate(sql);
                }

                /*statement.executeUpdate("INSERT INTO company.employee" +
                        "(login, password, fio, department, city, salary, phoneNumber, email) VALUES " +
                        "('login', 'pasword', 'fio', 'department', 'city', 123.45, 'phoneNumber', 'email');");

                statement.executeUpdate("INSERT INTO company.employee" +
                        "(login, password, fio, department, city, salary, phoneNumber, email) VALUES " +
                        "('login2', 'pasword2', 'fio2', 'department2', 'city2', 123.452, 'phoneNumber2', 'email2');");

                statement.executeUpdate("INSERT INTO company.employee" +
                        "(login, password, fio, department, city, salary, phoneNumber, email) VALUES " +
                        "('login3', 'pasword3', 'fio3', 'department3', 'city3', 123.453, 'phoneNumber3', 'email3');");

                statement.executeUpdate("INSERT INTO company.employee" +
                        "(login, password, fio, department, city, salary, phoneNumber, email) VALUES " +
                        "('login4', 'pasword4', 'fio4', 'department4', 'city4', 123.454, 'phoneNumber4', 'email4');");

                statement.executeUpdate("INSERT INTO company.role(name) VALUES ('admin');");
                statement.executeUpdate("INSERT INTO company.role(name) VALUES ('user');");
                statement.executeUpdate("INSERT INTO company.employee_role(employeeId, roleId) VALUES (1, 1);");
                statement.executeUpdate("INSERT INTO company.employee_role(employeeId, roleId) VALUES (2, 2);");
                statement.executeUpdate("INSERT INTO company.employee_role(employeeId, roleId) VALUES (3, 1);");
                statement.executeUpdate("INSERT INTO company.position(name) VALUES ('position');");
                statement.executeUpdate("INSERT INTO company.position(name) VALUES ('position2');");
                statement.executeUpdate("INSERT INTO company.employee_position(employeeId, positionId) VALUES (1, 1);");
                statement.executeUpdate("INSERT INTO company.employee_position(employeeId, positionId) VALUES (2, 2);");
                statement.executeUpdate("INSERT INTO company.employee_position(employeeId, positionId) VALUES (3, 1);");*/

                //GET ALL
                System.out.println("===================== GETTING ALL ROLES =======================");
                ResultSet resultSet = statement.executeQuery("SELECT * FROM company.role");
                while (resultSet.next()) {
                    long id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    System.out.println("id = " + id + ", name = " + name);
                }

                //ADD
                System.out.println("===================== ADDING =======================");
                statement.executeUpdate("INSERT INTO company.role(name) VALUES ('JDBC');");

                resultSet = statement.executeQuery("SELECT * FROM company.role");
                while (resultSet.next()) {
                    long id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    System.out.println("id = " + id + ", name = " + name);
                }

                //UPDATE
                System.out.println("===================== UDPATING =======================");
                resultSet = statement.executeQuery("SELECT id FROM role WHERE name = 'JDBC'");
                long id = 1;
                while (resultSet.next()) {
                    id = resultSet.getLong("id");
                }
                statement.executeUpdate("UPDATE role SET name='nameFromJDBC' WHERE id='" + id + "'");

                resultSet = statement.executeQuery("SELECT * FROM company.role");
                while (resultSet.next()) {
                    long id2 = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    System.out.println("id = " + id2 + ", name = " + name);
                }

                //DELETE
                System.out.println("===================== DELETING =======================");
                statement.executeUpdate("DELETE FROM role WHERE id='" + id + "'");
                resultSet = statement.executeQuery("SELECT * FROM role");
                while (resultSet.next()) {
                    id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    System.out.println("id = " + id + ", name = " + name);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new JDBC().jdbc();
    }
}

