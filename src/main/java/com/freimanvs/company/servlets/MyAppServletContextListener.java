package com.freimanvs.company.servlets;

import com.freimanvs.company.util.HibernateUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyAppServletContextListener implements ServletContextListener{

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        try {
            HibernateUtil.getSessionFactory().close();
        } catch (Exception e) {
            throw new RuntimeException("hibernate factory was not closed");
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
    }
}