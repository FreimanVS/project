package com.freimanvs.company.rest.beans;

import com.freimanvs.company.rest.beans.interfaces.CalculateDifferentialBean;

import javax.ejb.Stateless;

@Stateless
public class CalculateDifferentialBeanImpl implements CalculateDifferentialBean {

    @Override
    public String calculate(int t, double kr, double st) {
        st = st / 100 / 12;
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= t; i++) {
            double answer = kr / t + kr * (t - i + 1) * st / t;
            result.append(String.format("<div>%10.4f / %d + %10.4f * (%d - %d + 1) * %10.4f / %d = %10.1f</div>",
                    kr, t, kr, t, i, st, t, answer));
        }
        return result.toString();
    }
}
