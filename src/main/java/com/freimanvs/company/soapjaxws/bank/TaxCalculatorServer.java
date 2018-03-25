package com.freimanvs.company.soapjaxws.bank;

import javax.xml.ws.Endpoint;

public class TaxCalculatorServer {
    public static void main(String[] args) {
        Object obj = new TaxCalculatorService();
        String addr = "http://localhost:8080/company/TaxCalculatorService";

        Endpoint endpoint = Endpoint.create(obj);
        endpoint.publish(addr);

//        endpoint.stop();
    }
}
