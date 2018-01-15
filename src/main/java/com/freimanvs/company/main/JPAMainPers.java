package com.freimanvs.company.main;

import com.freimanvs.company.entities.Role;
import com.freimanvs.company.service.RoleServicePers;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAMainPers {
    public static final String PERSISTENCE_UNIT_NAME = "jpa";
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    public static void main(String[] args) {
        try {
            //role service
            RoleServicePers roleService = new RoleServicePers(emf);

            //a full list of roles
            System.out.println("=================== A FULL LIST OF ROLES ====================");
            roleService.getList().forEach(role -> System.out.print(role.getName() + " | "));
            System.out.println();

            //added a new role
            System.out.println("========================= ADDED A NEW ROLE ===========================");
            long id = roleService.add(new Role("test"));
            System.out.println("new Role has been added");
            System.out.println("ID: " + id);
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
        } finally {
            emf.close();
        }
    }

}
