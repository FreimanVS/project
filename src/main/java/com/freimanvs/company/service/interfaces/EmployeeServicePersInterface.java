package com.freimanvs.company.service.interfaces;

import com.freimanvs.company.entities.Employee;
import com.freimanvs.company.service.Service;

import javax.ejb.Remote;

@Remote
public interface EmployeeServicePersInterface extends Service<Employee> {
}
