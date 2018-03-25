package com.freimanvs.company.soapjaxws.bank;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface TaxCalculatorPort {
    @WebMethod
    @WebResult
    double exec(@WebParam double d0,
                @WebParam double r0,
                @WebParam double ns);
}