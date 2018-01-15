package com.freimanvs.company.controllers;

import com.freimanvs.company.entities.Position;
import com.freimanvs.company.service.PositionService;
import com.freimanvs.company.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PositionController {
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static Session session = sessionFactory.openSession();

    public static void main(String[] args) {
        try {
            //service
            PositionService positionService = new PositionService(session);

            //a full list of objects
            System.out.println("=================== A FULL LIST OF OBJECTS ====================");
            positionService.getList().forEach(obj -> System.out.print(obj.getName() + " | "));
            System.out.println();

            //added a new object
            System.out.println("========================= ADDED A NEW OBJECT ===========================");
            long id = positionService.add(new Position("test"));
            System.out.println("new user has been added");
            positionService.getList().forEach(obj -> System.out.print(obj.getName() + " | "));
            System.out.println();

            //changing the new object
            System.out.println("========================= CHANGING THE NEW OBJECT ===========================");
            positionService.updateById(id, new Position("newNameforTheUser"));
            System.out.println("The user's name has been changed");

            //getting by id
            System.out.println("========================= GETTING AN OBJECT BY ID ===========================");
            System.out.println("getting user by id = [" + id + "]...");
            Position ob = positionService.getById(id);
            System.out.println("The new name of the user with id " + id + " is " + ob.getName());
            System.out.println("a full list of them is: ");
            positionService.getList().forEach(obj -> System.out.print(obj.getName() + " | "));
            System.out.println();

            //deleted the new role
            System.out.println("========================= DELETING THE NEW OBJECT ===========================");
            positionService.deleteById(id);
            System.out.println("the new user has been deleted");
            positionService.getList().forEach(obj -> System.out.print(obj.getName() + " | "));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
