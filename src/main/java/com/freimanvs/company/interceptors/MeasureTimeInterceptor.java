package com.freimanvs.company.interceptors;

import com.freimanvs.company.interceptors.bindings.Measurable;
import com.freimanvs.company.interceptors.dao.interfaces.PerformanceDAO;
import com.freimanvs.company.interceptors.models.Performance;

import javax.annotation.Priority;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Measurable
@Interceptor
@Priority(0)
@Dependent
public class MeasureTimeInterceptor {

    @EJB
    private PerformanceDAO performanceDAO;

    @AroundInvoke
    public Object measure(InvocationContext ic) throws Exception {

        Long start = System.currentTimeMillis();

        Object value = ic.proceed();

        Long ms = System.currentTimeMillis() - start;
        String name = ic.getMethod().toString();

//        System.out.println("Execution of " + name +
//                ", time is " + ms + " ms.");

        performanceDAO.add(new Performance(name, ms));
        return value;
    }
}
