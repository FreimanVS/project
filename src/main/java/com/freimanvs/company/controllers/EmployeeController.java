package com.freimanvs.company.controllers;

import com.freimanvs.company.entities.Employee;
import com.freimanvs.company.service.EmployeeService;
import com.freimanvs.company.util.HibernateUtil;
import com.freimanvs.company.util.RestoreData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.HashSet;

public class EmployeeController {
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static Session session = sessionFactory.openSession();

    public static void main(String[] args) {

        try {
            //session
            Session session = sessionFactory.openSession();

            //service
            EmployeeService employeeService = new EmployeeService(session);

            //a full list of objects
            System.out.println("=================== A FULL LIST OF OBJECTS ====================");
            employeeService.getList().forEach(System.out::println);
            System.out.println();

            //added a new object
            System.out.println("========================= ADDED A NEW OBJECT ===========================");

            Employee newEmpl = new Employee();
            newEmpl.setLogin("test");
            newEmpl.setPassword("test");
            newEmpl.setFio("test");
            newEmpl.setDepartment("test");
            newEmpl.setCity("test");
            newEmpl.setSalary(99999);
            newEmpl.setPhoneNumber("+71234567890");
            newEmpl.setEmail("test@test.com");

            long id = employeeService.add(newEmpl);
            System.out.println("new user has been added");
            employeeService.getList().forEach(System.out::println);
            System.out.println();

            //changing the new object
            System.out.println("========================= CHANGING THE NEW OBJECT ===========================");

            Employee emp = employeeService.getById(id);
            emp.setLogin("newData");
            emp.setPassword("newData");
            emp.setFio("newData");
            emp.setDepartment("newData");
            emp.setPositions(new HashSet<>());
            employeeService.updateById(id, emp);

            System.out.println("The user's name has been changed");

            //getting by id
            System.out.println("========================= GETTING AN OBJECT BY ID ===========================");
            System.out.println("getting user by id = [" + id + "]...");
            Employee ob = employeeService.getById(id);
            System.out.println("The new name of the user with id " + id + " is " + ob.getFio());
            System.out.println("a full list of them is: ");
            employeeService.getList().forEach(System.out::println);
            System.out.println();

            //deleted the new object
            System.out.println("========================= DELETING THE NEW OBJECT ===========================");
            employeeService.deleteById(id);
            System.out.println("the new user has been deleted");
            employeeService.getList().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
