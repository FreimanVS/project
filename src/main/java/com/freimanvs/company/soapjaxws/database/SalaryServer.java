package com.freimanvs.company.soapjaxws.database;

import javax.xml.ws.Endpoint;

public class SalaryServer {
    public static void main(String[] args) {
        Object obj = new Salary();
        String addr = "http://localhost:8080/company/SalaryService";

        Endpoint endpoint = Endpoint.create(obj);
        endpoint.publish(addr);
    }
}
