package com.freimanvs.company.dao.interfaces;

import com.freimanvs.company.dao.DAO;
import com.freimanvs.company.entities.Employee;

import javax.ejb.Remote;

@Remote
public interface EmployeeDAOPersInterface extends DAO<Employee> {
}
