package com.freimanvs.company.soapjaxws.database;

import com.freimanvs.company.soapjaxws.database.fromwsdl.Salary;
import com.freimanvs.company.soapjaxws.database.fromwsdl.SalaryService;

import java.net.MalformedURLException;
import java.net.URL;

public class SalaryClient {
    private static final String HOST = "localhost";
    private static final String PORT = "8080";

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://" + HOST + ":" + PORT + "/company/SalaryService?wsdl");

        SalaryService salaryService = new SalaryService(url);
        Salary salaryClient = salaryService.getSalaryPort();
        double avg = salaryClient.avg();
        double max = salaryClient.max();
        System.out.printf("Average salary is %.1f\r\nMax salary is %.1f", avg, max);
    }
}
