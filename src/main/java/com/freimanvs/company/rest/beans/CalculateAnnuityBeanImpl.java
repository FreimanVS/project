package com.freimanvs.company.rest.beans;

import com.freimanvs.company.rest.beans.interfaces.CalculateAnnuityBean;

import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;

//@Stateless
@Dependent
public class CalculateAnnuityBeanImpl implements CalculateAnnuityBean {

    @Override
    public String calculate(int t,
                            double kr,
                            double st) {
        st = st / 100 / 12;
        double pl = kr * st / (1 - 1 / Math.pow ((1 + st), t));
        return String.format("<div>%10.4f * %10.4f / (1 - 1 / (1 + %10.4f) ^ %d) = %10.0f</div>",
                kr, st, st, t, pl);
    }
}
