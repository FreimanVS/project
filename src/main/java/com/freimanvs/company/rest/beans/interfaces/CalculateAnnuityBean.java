package com.freimanvs.company.rest.beans.interfaces;

import javax.ejb.Remote;

@Remote
public interface CalculateAnnuityBean {
    String calculate(int t,
                     double kr,
                     double st);
}
