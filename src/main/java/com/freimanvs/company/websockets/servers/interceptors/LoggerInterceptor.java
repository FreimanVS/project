package com.freimanvs.company.websockets.servers.interceptors;

import org.apache.log4j.Logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.websocket.Session;
import java.lang.reflect.Method;

public class LoggerInterceptor {

    private static final Logger LOGGER = Logger.getLogger(LoggerInterceptor.class);

    @AroundInvoke
    public Object log(InvocationContext ic) throws Exception {
        Object object = ic.proceed();

        Session session = null;
        Method method = ic.getMethod();
        String methodName = method.getName();
        if (methodName.contains("onOpen")) {
            for (Object param : method.getParameters()) {
                if (param instanceof Session)
                    session = (Session)param;
            }
            LOGGER.info("New session is opened: " + (session != null ? session.getId() : "no session"));

        } else if (methodName.contains("onMessage")) {
            String message = null;
            for (Object param : method.getParameters()) {
                if (param instanceof String)
                    message = (String)param;
                else if (param instanceof Session)
                    session = (Session)param;
            }
            LOGGER.info("received msg " + message + " from " + (session != null ? session.getId() : "no session"));

        } else if (methodName.contains("onClose")) {
            for (Object param : method.getParameters()) {
                if (param instanceof Session)
                    session = (Session)param;
            }
            LOGGER.info("New session is closed: " + (session != null ? session.getId() : "no session"));

        } else if (methodName.contains("onError")) {
            Throwable t = null;
            for (Object param : method.getParameters()) {
                if (param instanceof Session)
                    session = (Session)param;
                else if (param instanceof Throwable)
                    t = (Throwable)param;
                LOGGER.error("Error on session " + (session != null ? session.getId() : "no session")
                        + ": " + (t != null ? t.getLocalizedMessage() : "no exception"));
            }
        }

        return object;
    }
}
