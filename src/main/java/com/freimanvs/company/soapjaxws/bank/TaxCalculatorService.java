package com.freimanvs.company.soapjaxws.bank;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(serviceName = "TaxCalculatorService", name = "TaxCalculatorPort",
        endpointInterface = "com.freimanvs.company.soapjaxws.bank.TaxCalculatorPort")
public class TaxCalculatorService implements TaxCalculatorPort {

    @PostConstruct
    public void postConstruct() {

    }

    @WebMethod
    @WebResult
    public double exec(@WebParam double d0,
                       @WebParam double r0,
                       @WebParam double ns) {

        return (d0 - r0) * ns / 100;
    }

    @PreDestroy
    public void preDestroy() {

    }
}
