package com.freimanvs.company.soapjaxws.database.beans;

import com.freimanvs.company.entities.Employee;
import com.freimanvs.company.soapjaxws.database.beans.interfaces.SalaryBean;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class SalaryBeanImpl implements SalaryBean{

    @PersistenceContext(unitName = "mysqlejb")
    public EntityManager em;

    public SalaryBeanImpl() {
    }

    public Double getMax() {
        List<Employee> employees = em.createNamedStoredProcedureQuery("with_max_salary").getResultList();
        return employees.stream().map(Employee::getSalary).findAny().orElse(0.0);
    }

    public Double getAvg() {
        return (Double) em.createNativeQuery("CALL avg_salary();").getSingleResult();
    }
}
