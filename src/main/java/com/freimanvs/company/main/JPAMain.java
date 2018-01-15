package com.freimanvs.company.main;

import com.freimanvs.company.entities.Role;
import com.freimanvs.company.service.RoleService;
import com.freimanvs.company.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class JPAMain {
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static Session session = sessionFactory.openSession();

    public static void main(String[] args) {
        try {
            //role service
            RoleService roleService = new RoleService(session);

            //a full list of roles
            System.out.println("=================== A FULL LIST OF ROLES ====================");
            roleService.getList().forEach(role -> System.out.print(role.getName() + " | "));
            System.out.println();

            //added a new role
            System.out.println("========================= ADDED A NEW ROLE ===========================");
            long id = roleService.add(new Role("test"));
            System.out.println("new Role has been added");
            roleService.getList().forEach(role -> System.out.print(role.getName() + " | "));
            System.out.println();

            //changing the new role
            System.out.println("========================= CHANGING THE NEW ROLE ===========================");
            roleService.updateById(id, new Role("newNameforTheUser"));
            System.out.println("The user's name has been changed");

            //getting by id
            System.out.println("========================= GETTING ROLE BY ID ===========================");
            System.out.println("getting role by id = [" + id + "]...");
            Role role = roleService.getById(id);
            System.out.println("The new name of the user with id " + id + " is " + role.getName());
            System.out.println("a full list of them is: ");
            roleService.getList().forEach(r -> System.out.print(r.getName() + " | "));
            System.out.println();

            //deleted the new role
            System.out.println("========================= DELETING THE NEW ROLE ===========================");
            roleService.deleteById(id);
            System.out.println("the new user has been deleted");
            roleService.getList().forEach(theRole -> System.out.print(theRole.getName() + " | "));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
