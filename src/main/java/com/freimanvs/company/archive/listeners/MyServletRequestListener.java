package com.freimanvs.company.archive.listeners;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

//@WebListener
public class MyServletRequestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {

//        Enumeration<String> requestAttrs = sre.getServletRequest().getAttributeNames();
//        System.out.println("requestAttrs: ");
//        while (requestAttrs.hasMoreElements())
//            System.out.println(requestAttrs.nextElement());
//
//        Enumeration<String> contextAttrs = sre.getServletContext().getAttributeNames();
//        System.out.println("contextAttrs: ");
//        while (contextAttrs.hasMoreElements())
//            System.out.println(contextAttrs.nextElement());
    }
}
