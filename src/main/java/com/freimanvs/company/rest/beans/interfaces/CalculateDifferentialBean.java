package com.freimanvs.company.rest.beans.interfaces;

import javax.ejb.Remote;

@Remote
public interface CalculateDifferentialBean {
    String calculate(int t,
                     double kr,
                     double st);
}
