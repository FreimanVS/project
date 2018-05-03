package com.freimanvs.company.interceptors;

import com.freimanvs.company.interceptors.bindings.Logging;
import org.apache.log4j.Logger;

import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Logging
@Priority(1)
@Dependent
public class LoggerInterceptor {

    private static final Logger LOGGER = Logger.getLogger(LoggerInterceptor.class);

    @AroundInvoke
    public Object log(InvocationContext ic) throws Exception {
        LOGGER.debug(ic.getMethod() + " starting...");
        Object value = ic.proceed();
        LOGGER.debug(ic.getMethod() + " finished!");
        return value;
    }
}
