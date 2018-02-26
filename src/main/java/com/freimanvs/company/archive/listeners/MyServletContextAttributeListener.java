package com.freimanvs.company.archive.listeners;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

//@WebListener
public class MyServletContextAttributeListener implements ServletContextAttributeListener {
    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        System.out.println("Attribute " + event.getName() + " : " + event.getValue() + " has been added");
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        System.out.println("Attribute " + event.getName() + " : " + event.getValue() + " has been removed");

    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        System.out.println("Attribute " + event.getName() + " : " + event.getValue() + " has been replaced");

    }
}
