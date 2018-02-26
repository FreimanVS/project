package com.freimanvs.company.archive.listeners;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

//@WebListener
public class MyHttpSessionBindingListener implements HttpSessionBindingListener {
    //this method will be revoked by
    // getSession().setAttribute("nyHttpSessionBindingListener", new MyHttpSessionBindingListener());
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("event.getName() = " + event.getName());
        System.out.println("event.getValue() = " + event.getValue());
        System.out.println("event.getSession() = " + event.getSession());
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {

    }
}
