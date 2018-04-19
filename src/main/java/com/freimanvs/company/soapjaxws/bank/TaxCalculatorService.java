package com.freimanvs.company.soapjaxws.bank;

import com.freimanvs.company.soapjaxws.bank.beans.interfaces.TaxCalculatorBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(serviceName = "TaxCalculatorService", name = "TaxCalculatorPort",
        endpointInterface = "com.freimanvs.company.soapjaxws.bank.TaxCalculatorPort")
public class TaxCalculatorService implements TaxCalculatorPort {

//    @EJB
    @Inject
    private TaxCalculatorBean taxCalculatorBean;

    @PostConstruct
    public void postConstruct() {

    }

    @WebMethod
    @WebResult
    public double exec(@WebParam double d0,
                       @WebParam double r0,
                       @WebParam double ns) {

        return taxCalculatorBean.calculate();
    }

    @PreDestroy
    public void preDestroy() {

    }
}
