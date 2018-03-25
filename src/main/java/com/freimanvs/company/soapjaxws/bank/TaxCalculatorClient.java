package com.freimanvs.company.soapjaxws.bank;

import com.freimanvs.company.soapjaxws.bank.fromwsdl.TaxCalculatorPort;
import com.freimanvs.company.soapjaxws.bank.fromwsdl.TaxCalculatorService;

import java.net.MalformedURLException;
import java.net.URL;

public class TaxCalculatorClient {
    private static final String HOST = "localhost";
    private static final String PORT = "8080";

    private static final double d0 = 1000000;
    private static final double r0 = 200000;
    private static final double ns = 20;

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://" + HOST + ":" + PORT + "/company/TaxCalculatorService?wsdl");

        TaxCalculatorService taxCalculatorService = new TaxCalculatorService(url);
        TaxCalculatorPort taxCalculatorPortPort = taxCalculatorService.getTaxCalculatorPortPort();
        double result = taxCalculatorPortPort.exec(d0, r0, ns);
        System.out.printf("Result is %.0f", result);
    }
}
