package com.freimanvs.company.archive.listeners;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

//@WebListener
public class MyHttpSessionActivationListener implements HttpSessionActivationListener {
    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
        System.out.println("se.getSession() = " + se.getSession());
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent se) {

    }
}
