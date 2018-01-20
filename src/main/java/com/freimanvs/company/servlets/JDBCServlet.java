package com.freimanvs.company.servlets;

import com.freimanvs.company.entities.Employee;
import com.freimanvs.company.util.RestoreData;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/JDBCServlet")
public class JDBCServlet extends HttpServlet {

    @Resource(name = "jdbc/MySQLDS")
//    @Resource(name = "jdbc/H2DS")
    private DataSource ds;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //initialization
//        RestoreData.restoreJPA();

        try (Connection conn = ds.getConnection();
            Statement statement = conn.createStatement()) {

            /*statement.executeUpdate("CREATE SCHEMA IF NOT EXISTS company");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS company.role (\n" +
                            "id BIGINT NOT NULL AUTO_INCREMENT,\n" +
                            "name VARCHAR(100) NOT NULL,\n" +
                            "PRIMARY KEY (id),\n" +
                            "CONSTRAINT uniqRole UNIQUE (name)\n" +
                            ")");*/
//            statement.executeUpdate("INSERT INTO company.role(name) VALUES ('ivan3')");

            ResultSet resultSet = statement.executeQuery("SELECT * FROM company.role");
            StringBuilder sb = new StringBuilder();
            while(resultSet.next()){
                sb.append(resultSet.getLong("id"))
                        .append(" | ")
                        .append(resultSet.getString("name"))
                        .append("\r\n");
            }
            try (PrintWriter pw = response.getWriter()) {
                pw.println(sb.toString());
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        /*try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement("select * from company.role");
             ResultSet resultSet = ps.executeQuery()){

            StringBuilder sb = new StringBuilder();
            while(resultSet.next()){
                sb.append(resultSet.getLong("id"))
                        .append(" | ")
                        .append(resultSet.getString("name"))
                        .append("\r\n");
            }
            try (PrintWriter pw = response.getWriter()) {
                pw.println(sb.toString());
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }*/
    }
}