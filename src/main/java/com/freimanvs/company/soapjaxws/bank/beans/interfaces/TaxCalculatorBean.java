package com.freimanvs.company.soapjaxws.bank.beans.interfaces;

import javax.ejb.Remote;
import java.net.URL;

@Remote
public interface TaxCalculatorBean {
    Double exec(URL url);
    Double calculate();

    Double getD0();
    void setD0(Double d0);
    Double getR0();
    void setR0(Double r0);
    Double getNs();
    void setNs(Double ns);
}
