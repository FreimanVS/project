package com.freimanvs.company.archive.listeners;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

//@WebListener
public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println("Attribute " + event.getName() + " : " + event.getValue() + " has been added");
        HttpSession httpSession = event.getSession();
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.println("Attribute " + event.getName() + " : " + event.getValue() + " has been removed");

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.println("Attribute " + event.getName() + " : " + event.getValue() + " has been replaced");
    }
}
