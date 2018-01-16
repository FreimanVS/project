package com.freimanvs.company.servlets;

import com.freimanvs.company.util.HibernateUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyAppServletContextListener implements ServletContextListener{

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        HibernateUtil.getSessionFactory().close();
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
    }
}