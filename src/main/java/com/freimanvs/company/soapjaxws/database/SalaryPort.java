package com.freimanvs.company.soapjaxws.database;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface SalaryPort {

    @WebMethod
    @WebResult
    double max();

    @WebMethod
    @WebResult
    double avg();
}