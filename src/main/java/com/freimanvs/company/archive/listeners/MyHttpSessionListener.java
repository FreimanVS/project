package com.freimanvs.company.archive.listeners;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//@WebListener
public class MyHttpSessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
//        System.out.println("se.getSource() = " + se.getSource());
//        System.out.println("se.getSession().toString() = " + se.getSession().toString());
//        System.out.println("se.getSession().getId() = " + se.getSession().getId());
//        se.getSession().setAttribute("abc", "asdfasdf");
//        Enumeration<String> an = se.getSession().getAttributeNames();
//        while (an.hasMoreElements())
//            System.out.println(an.nextElement());

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("session is destroyed");
    }
}
