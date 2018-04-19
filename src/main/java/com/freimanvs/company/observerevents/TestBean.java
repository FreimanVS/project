package com.freimanvs.company.observerevents;

import com.freimanvs.company.interceptors.bindings.Measurable;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import javax.enterprise.event.ObservesAsync;
import java.io.IOException;

@Dependent
public class TestBean {

    public void test(@Observes TestObj o) {
        try {
            System.out.println(o.handleEvent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
