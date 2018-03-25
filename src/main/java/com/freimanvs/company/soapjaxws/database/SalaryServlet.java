package com.freimanvs.company.soapjaxws.database;

import com.freimanvs.company.soapjaxws.database.fromwsdl.Salary;
import com.freimanvs.company.soapjaxws.database.fromwsdl.SalaryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

@WebServlet("/salary")
public class SalaryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String host = req.getLocalAddr().contains("0:0:0:0:0:0:1") ? "localhost" : req.getLocalAddr();
        String port = String.valueOf(req.getLocalPort());
        URL url = new URL("http://" + host + ":" + port + "/company/SalaryService?wsdl");

        SalaryService salaryService = new SalaryService(url);

        Salary salaryClient = salaryService.getSalaryPort();
        double avg = salaryClient.avg();
        double max = salaryClient.max();

        try (PrintWriter pw = resp.getWriter()) {
            pw.printf("Average salary is %.1f\r\n" +
                    "Max salary is %.1f", avg, max);
        }
    }
}
