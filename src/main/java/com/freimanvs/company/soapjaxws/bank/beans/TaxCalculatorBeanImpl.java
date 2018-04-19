package com.freimanvs.company.soapjaxws.bank.beans;

import com.freimanvs.company.interceptors.bindings.Measurable;
import com.freimanvs.company.soapjaxws.bank.beans.interfaces.TaxCalculatorBean;
import com.freimanvs.company.soapjaxws.bank.fromwsdl.TaxCalculatorService;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import java.net.URL;

//@Stateless
@Measurable
@Dependent
public class TaxCalculatorBeanImpl implements TaxCalculatorBean {

    private Double d0;
    private Double r0;
    private Double ns;

    public TaxCalculatorBeanImpl() {
    }

    @Override
    public Double exec(URL url) {
        TaxCalculatorService taxCalculatorService = new TaxCalculatorService(url);
        return taxCalculatorService.getTaxCalculatorPortPort().exec(d0, r0, ns);
    }

    @Override
    public Double calculate() {
        return (d0 - r0) * ns / 100;
    }

    public Double getD0() {
        return d0;
    }

    public void setD0(Double d0) {
        this.d0 = d0;
    }

    public Double getR0() {
        return r0;
    }

    public void setR0(Double r0) {
        this.r0 = r0;
    }

    public Double getNs() {
        return ns;
    }

    public void setNs(Double ns) {
        this.ns = ns;
    }
}
