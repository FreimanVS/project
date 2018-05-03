package com.freimanvs.company.service;

import com.freimanvs.company.dao.interfaces.EmployeeDAOPersInterface;
import com.freimanvs.company.dao.interfaces.RoleDAOPersInterface;
import com.freimanvs.company.entities.Employee;
import com.freimanvs.company.entities.Role;
import com.freimanvs.company.service.interfaces.AdminService;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@RolesAllowed({"admin"})
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AdminServiceImpl implements AdminService {

    @EJB
    private EmployeeDAOPersInterface employeeDAO;

    @EJB
    private RoleDAOPersInterface roleDAO;

    @Override
    public void assignRoles(String login, String[] roles) {
        Employee employee = employeeDAO.getByUnique("login", login);
        Set<Role> setOfRoles = Arrays.stream(roles).map(name -> roleDAO.getByUnique("name", name))
                .collect(Collectors.toSet());
        employee.setRoles(setOfRoles);
        employeeDAO.updateById(employee.getId(), employee);
    }
}
