package com.freimanvs.company.interceptors;

import org.apache.log4j.Logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class InitDbInterceptor {

    private static final Logger LOGGER = Logger.getLogger(InitDbInterceptor.class);

    @AroundInvoke
    public Object log(InvocationContext ic) {
        LOGGER.info(ic.getMethod() + "starting...");
        Object result = null;
        try {
            result = ic.proceed();
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage());
            e.printStackTrace();
        }
        LOGGER.info(ic.getMethod() + "finished!");
        return result;
    }
}
