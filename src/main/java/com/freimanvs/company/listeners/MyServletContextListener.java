package com.freimanvs.company.listeners;

import com.freimanvs.company.util.interfaces.DbXMLBean;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.sql.*;

@WebListener
public class MyServletContextListener implements ServletContextListener {

    private static final String TEST_DATA_FILE_LOCATION = "DB_XML_location";

    private static String IP;
    private static String URL;
    private static String LOGIN;
    private static String PASSWORD ;

    @EJB
    private DbXMLBean dbXMLBean;

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        IP = arg0.getServletContext().getInitParameter("DB_IP");
        URL = "jdbc:mysql://" + IP + ":3306/sys?useSSL=false";
        LOGIN = "root";
        PASSWORD = "pass";

        //to sleep until a database is ready
        while (!connIsOk()) {
            System.out.printf("DB is not ready yet or incorrect data:\r\n" +
                    "ULR: %s\r\n" +
                    "LOGIN: %s\r\n" +
                    "PASSWORD: %s\r\n", URL, LOGIN, PASSWORD);
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//
        //XML to DB
        try {
            System.out.println("XML to DB process...");
            String uri = arg0.getServletContext().getInitParameter(TEST_DATA_FILE_LOCATION);
            dbXMLBean.xmlToDB(Paths.get(new URI(uri)));
            System.out.println("XML to DB has been completed!");
        } catch (URISyntaxException e) {
            System.out.println("XML to DB ERROR!");
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        //DB to XML
        try {
            System.out.println("DB to XML process...");
            String uri = arg0.getServletContext().getInitParameter(TEST_DATA_FILE_LOCATION);
            dbXMLBean.dbToXml(Paths.get(new URI(uri)));
            System.out.println("DB to XML has been completed!");
        } catch (URISyntaxException e) {
            System.out.println("DB to XML ERROR!");
            e.printStackTrace();
        }
    }

    private static boolean connIsOk() {
        try {
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
        } catch (Exception e) {
            return false;
        }

        try (Connection conn = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT 1")) {

            StringBuilder sb = new StringBuilder();
            while(resultSet.next()){
                sb.append(resultSet.getInt("1"));
            }

            return "1".equals(sb.toString());

        } catch (SQLException e) {
            return false;
        }
    }
}