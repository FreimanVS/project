package com.freimanvs.company.websockets.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import javax.websocket.Session;

@Aspect
public class WebSocketLogger {

    private static final Logger LOGGER = Logger.getLogger(WebSocketLogger.class);

    @Around("execution(* com.freimanvs.company.websockets..*.onOpen(..))")
    public Object onOpen(ProceedingJoinPoint pjp) throws Throwable {

        Session session = null;
        for (Object param : pjp.getArgs()) {
            if (param instanceof Session)
                session = (Session) param;
        }

        LOGGER.info("New session is opened: " + session.getId());
        return pjp.proceed();
    }

    @Around("execution(* com.freimanvs.company.websockets..*.onMessage(..))")
    public Object onMessage(ProceedingJoinPoint pjp) throws Throwable {

        Session session = null;
        String msg = null;
        for (Object param : pjp.getArgs()) {
            if (param instanceof Session)
                session = (Session) param;
            else if (param instanceof String)
                msg = (String)param;
        }

        LOGGER.info("received msg " + msg + " from " + session.getId());
        return pjp.proceed();
    }

    @Around("execution(* com.freimanvs.company.websockets..*.onClose(..))")
    public Object onClose(ProceedingJoinPoint pjp) throws Throwable {

        Session session = null;
        for (Object param : pjp.getArgs()) {
            if (param instanceof Session)
                session = (Session) param;
        }

        LOGGER.info("New session is closed: " + session.getId());
        return pjp.proceed();
    }

    @Around("execution(* com.freimanvs.company.websockets..*.onError(..))")
    public Object onError(ProceedingJoinPoint pjp) throws Throwable {

        Session session = null;
        Throwable t = null;
        for (Object param : pjp.getArgs()) {
            if (param instanceof Session)
                session = (Session) param;
            else if (param instanceof Throwable)
                t = (Throwable)param;
        }

        LOGGER.error("Error on session " + session.getId() + ": " + t.getLocalizedMessage());
        return pjp.proceed();
    }
}
