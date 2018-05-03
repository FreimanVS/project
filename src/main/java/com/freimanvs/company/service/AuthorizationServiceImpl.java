package com.freimanvs.company.service;

import com.freimanvs.company.entities.Employee;
import com.freimanvs.company.interceptors.bindings.Measurable;
import com.freimanvs.company.security.beans.interfaces.SecurityBean;
import com.freimanvs.company.service.interfaces.AuthorizationService;
import com.freimanvs.company.service.interfaces.EmployeeServicePersInterface;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.security.Principal;
import java.util.List;

@Measurable
@Dependent
public class AuthorizationServiceImpl implements AuthorizationService {

    @Inject
    private EmployeeServicePersInterface employeeService;

    @Inject
    private SecurityBean securityBean;

    @Inject
    private Principal principal;

    @Override
    public boolean isAuthorized(String login, String password) {
        List<Employee> employees = employeeService.getList();
        return employees != null && employees.stream().anyMatch(e -> e.getLogin().equals(login)
                && e.getPassword().equals(securityBean.encodeSha(password)));
    }
}
