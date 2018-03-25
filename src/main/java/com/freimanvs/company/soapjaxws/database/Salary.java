package com.freimanvs.company.soapjaxws.database;

import com.freimanvs.company.entities.Employee;
import com.freimanvs.company.util.HibernateUtil;
import org.hibernate.Session;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

@WebService
public class Salary {

    @WebMethod
    @WebResult
    public double max() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Employee> employees = session.getNamedProcedureCall("with_max_salary").getResultList();
        session.close();

        return employees.stream().map(Employee::getSalary).findAny().orElse(0.0);
    }

    @WebMethod
    @WebResult
    public double avg() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        double avg = (Double) session.createNativeQuery("CALL avg_salary();").getSingleResult();
        session.close();
        return avg;
    }
}
