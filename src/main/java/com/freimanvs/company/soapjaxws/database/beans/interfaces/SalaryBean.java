package com.freimanvs.company.soapjaxws.database.beans.interfaces;

import javax.ejb.Remote;

@Remote
public interface SalaryBean {
    Double getMax();
    Double getAvg();
}
