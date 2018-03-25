package com.freimanvs.company.soapjaxws.bank;

import com.freimanvs.company.soapjaxws.bank.fromwsdl.TaxCalculator;
import com.freimanvs.company.soapjaxws.bank.fromwsdl.TaxCalculatorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

@WebServlet("/taxcalc")
public class TaxCalculatorServlet extends HttpServlet {

//    @WebServiceRef
//    private TaxCalculatorService service;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String host = req.getLocalAddr().contains("0:0:0:0:0:0:1") ? "localhost" : req.getLocalAddr();
        String port = String.valueOf(req.getLocalPort());
        URL url = new URL("http://" + host + ":" + port + "/company/TaxCalculatorService?wsdl");

        TaxCalculatorService taxCalculatorService = new TaxCalculatorService(url);

        double d0 = Double.parseDouble(req.getParameter("d0"));
        double r0 = Double.parseDouble(req.getParameter("r0"));
        double ns = Double.parseDouble(req.getParameter("ns"));

        TaxCalculator taxCalculatorClient = taxCalculatorService.getTaxCalculatorPort();
        double result = taxCalculatorClient.exec(d0, r0, ns);

        try (PrintWriter pw = resp.getWriter()) {
            pw.printf("Result is %.0f", result);
        }
    }
}
