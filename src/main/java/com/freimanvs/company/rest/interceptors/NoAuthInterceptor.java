package com.freimanvs.company.rest.interceptors;

import com.freimanvs.company.entities.Employee;
import com.freimanvs.company.entities.User;
import com.freimanvs.company.service.EmployeeServicePers;
import com.freimanvs.company.service.interfaces.AuthorizationService;
import com.freimanvs.company.service.interfaces.EmployeeServicePersInterface;

import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;

public class NoAuthInterceptor {

    @Inject
    private AuthorizationService authorizationService;

    @AroundInvoke
    public Object auth(InvocationContext ic) throws Exception {
        Object[] params = ic.getParameters();
        for (Object param : params) {
            if (param instanceof User
                    && !authorizationService.isAuthorized(((User) param).getLogin(), ((User) param).getPassword())) {
                throw new NotAuthorizedException(param.toString());
            }
        }
        return ic.proceed();
    }
}
