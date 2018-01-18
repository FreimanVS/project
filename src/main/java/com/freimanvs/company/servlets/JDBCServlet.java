package com.freimanvs.company.servlets;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/JDBCServlet")
public class JDBCServlet extends HttpServlet {

    @Resource(name = "jdbc/MySQLDS")
    private DataSource ds;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        try (Connection conn = ds.getConnection();
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
        }
    }
}