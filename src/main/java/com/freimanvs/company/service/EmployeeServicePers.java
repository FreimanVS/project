package com.freimanvs.company.service;

import com.freimanvs.company.dao.interfaces.EmployeeDAOPersInterface;
import com.freimanvs.company.entities.Employee;
import com.freimanvs.company.interceptors.bindings.Measurable;
import com.freimanvs.company.security.beans.interfaces.SecurityBean;
import com.freimanvs.company.service.interfaces.EmployeeServicePersInterface;

import javax.ejb.*;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import java.security.Principal;
import java.util.List;

//@Stateless
@Measurable
@Dependent
public class EmployeeServicePers implements EmployeeServicePersInterface {

    @EJB
    private EmployeeDAOPersInterface employeeDAO;

//    @EJB
    @Inject
    private SecurityBean securityBean;

    @Inject
    private Principal principal;

    public EmployeeServicePers() {
    }

    @Override
    public long add(Employee obj) {

        if (employeeDAO.getById(obj.getId()) != null) {
            throw new EntityExistsException("The entity already exists: " + obj.toString());
        }

        //encode password
        obj.setPassword(securityBean.encodeSha(obj.getPassword()));

        return employeeDAO.add(obj);
    }

    @Override
    public List<Employee> getList() {
        return employeeDAO.getList();
    }

    @Override
    public Employee getById(long id) {
        return employeeDAO.getById(id);
    }

    @Override
    public void deleteById(long id) {
        employeeDAO.deleteById(id);
    }

    @Override
    public void updateById(long id, Employee obj) {
        employeeDAO.updateById(id, obj);
    }
}
