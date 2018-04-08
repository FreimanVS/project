package com.freimanvs.company.soapjaxws.database;

import com.freimanvs.company.soapjaxws.database.beans.interfaces.SalaryBean;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(serviceName = "SalaryService", name = "SalaryPort",
        endpointInterface = "com.freimanvs.company.soapjaxws.database.SalaryPort")
public class SalaryService implements SalaryPort {

    @EJB
    private SalaryBean salaryBean;

    @WebMethod
    @WebResult
    public double max() {
        return salaryBean.getMax();
    }

    @WebMethod
    @WebResult
    public double avg() {
        return salaryBean.getAvg();
    }
}
