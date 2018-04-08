package com.freimanvs.company.soapjaxws.bank;


import com.freimanvs.company.soapjaxws.bank.beans.interfaces.TaxCalculatorBean;

import javax.ejb.EJB;
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

    @EJB
    private TaxCalculatorBean taxCalculatorBean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String host = req.getLocalAddr().contains("0:0:0:0:0:0:1") ? "localhost" : req.getLocalAddr();
        String port = String.valueOf(req.getLocalPort());
        URL url = new URL("http://" + host + ":" + port + "/company/TaxCalculatorService?wsdl");


        double d0 = Double.parseDouble(req.getParameter("d0"));
        double r0 = Double.parseDouble(req.getParameter("r0"));
        double ns = Double.parseDouble(req.getParameter("ns"));


        taxCalculatorBean.setD0(d0);
        taxCalculatorBean.setR0(r0);
        taxCalculatorBean.setNs(ns);
        double result = taxCalculatorBean.exec(url);

        try (PrintWriter pw = resp.getWriter()) {
            pw.printf("Result is %.0f", result);
        }
    }
}
