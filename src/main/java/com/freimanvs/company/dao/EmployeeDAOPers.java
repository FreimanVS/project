package com.freimanvs.company.dao;

import com.freimanvs.company.dao.interfaces.EmployeeDAOPersInterface;
import com.freimanvs.company.entities.Employee;
import com.freimanvs.company.interceptors.bindings.Logging;
import org.hibernate.Hibernate;

import javax.annotation.security.RolesAllowed;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@Logging
public class EmployeeDAOPers implements EmployeeDAOPersInterface {

    @PersistenceContext(unitName = "mysqlejb")
    private EntityManager em;

    public EmployeeDAOPers() {
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public long add(Employee obj) {

        Employee newObj = em.merge(obj);
        em.flush();
        return newObj.getId();
    }

    @Override
    public List<Employee> getList() {
        List<Employee> list = em.createQuery("SELECT c FROM Employee c", Employee.class).getResultList();

        if (list != null && !list.isEmpty())
            list.forEach(this::initialize);

        return list;
    }

    @Override
    public Employee getById(long id) {
        Employee emp = em.find(Employee.class, id);
        initialize(emp);
        return emp;
    }

    @Override
    public Employee getByUnique(String uniqParam, String value) {

        TypedQuery<Employee> query;

        if ("login".equals(uniqParam)) {
            query = em.createQuery("select DISTINCT e from Employee e"
                    +" WHERE e.user.login = :theValue", Employee.class)
                    .setParameter("theValue", value);
        } else {
            query = em.createQuery("select DISTINCT e from Employee e"
                    +" WHERE e." + uniqParam + " = :theValue", Employee.class)
                    .setParameter("theValue", value);
        }

        List<Employee> list = query.getResultList();

        if (list != null && !list.isEmpty()) {
            list.forEach(this::initialize);
            return list.get(0);
        } else {
            return null;
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void deleteById(long id) {
        em.remove(getById(id));
        em.flush();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void updateById(long id, Employee obj) {
        Employee objFromDB = getById(id);
        objFromDB.setLogin(obj.getLogin());
        objFromDB.setPassword(obj.getPassword());
        objFromDB.setFio(obj.getFio());
        objFromDB.setDepartment(obj.getDepartment());
        objFromDB.setCity(obj.getCity());
        objFromDB.setSalary(obj.getSalary());
        objFromDB.setPhoneNumber(obj.getPhoneNumber());
        objFromDB.setEmail(obj.getEmail());
        objFromDB.setAge(obj.getAge());
        objFromDB.setPositions(obj.getPositions());
        objFromDB.setRoles(obj.getRoles());
        em.flush();
    }

    private void initialize(Employee e) {
        if (e != null) {
            Hibernate.initialize(e);
            e.getPositions().forEach(Hibernate::initialize);
            e.getRoles().forEach(Hibernate::initialize);
        }
    }
}
