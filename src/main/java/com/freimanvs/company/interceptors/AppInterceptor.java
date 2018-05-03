package com.freimanvs.company.interceptors;

import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.interceptor.InvocationContext;

public class AppInterceptor {

    private static final Logger LOGGER = Logger.getLogger(AppInterceptor.class);

    @PostConstruct
    public void init(InvocationContext ic) {
        LOGGER.info("APPLICATION STARTED!");
    }

    @PreDestroy
    public void destroy(InvocationContext ic) {
        LOGGER.info("APPLICATION FINISHED!");
    }

}
