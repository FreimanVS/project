package com.freimanvs.company.rest.interceptors;

import com.freimanvs.company.entities.Employee;
import com.freimanvs.company.rest.EmployeeRest;
import com.freimanvs.company.rest.PositionRest;
import com.freimanvs.company.rest.RoleRest;
import com.freimanvs.company.service.EmployeeServicePers;
import com.freimanvs.company.service.interfaces.EmployeeServicePersInterface;
import com.freimanvs.company.service.interfaces.PositionServicePersInterface;
import com.freimanvs.company.service.interfaces.RoleServicePersInterface;

import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;

public class NotFoundInterceptor {

//    private EmployeeServicePersInterface employeeService
//            = CDI.current().select(EmployeeServicePers.class).get()
//            ;

    @Inject
    private EmployeeServicePersInterface employeeService;

    @Inject
    private PositionServicePersInterface positionService;

    @Inject
    private RoleServicePersInterface roleService;

    @AroundInvoke
    public Object notFound(InvocationContext ic) throws Exception {
        Object[] params = ic.getParameters();
        if (ic.getTarget() instanceof EmployeeRest) {
            for (Object param : params) {
                if (param instanceof Long
                        && employeeService.getById((Long) param) == null) {
                    throw new NotFoundException();
                }
            }
        } else if (ic.getTarget() instanceof PositionRest) {
            for (Object param : params) {
                if (param instanceof Long
                        && positionService.getById((Long) param) == null) {
                    throw new NotFoundException();
                }
            }
        } else if (ic.getTarget() instanceof RoleRest) {
            for (Object param : params) {
                if (param instanceof Long
                        && roleService.getById((Long) param) == null) {
                    throw new NotFoundException();
                }
            }
        }

        return ic.proceed();
    }
}
