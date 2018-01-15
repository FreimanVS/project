package com.freimanvs.company.dao;

import com.freimanvs.company.entities.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeDAO implements DAO<Employee> {
    private Session session;

    public EmployeeDAO() {
    }

    public EmployeeDAO(Session session) {
        this.session = session;
    }

    @Override
    public long add(Employee obj) {
        session.save(obj);
        return obj.getId();
    }

    @Override
    public List<Employee> getList() {
        Query<Employee> query = session.createQuery("from Employee", Employee.class);
        return query.getResultList();
    }

    @Override
    public Employee getById(long id) {
        return session.get(Employee.class, id);
    }

    @Override
    public void deleteById(long id) {
        Employee objFromDB = session.byId(Employee.class).load(id);
        session.delete(objFromDB);
    }

    @Override
    public void updateById(long id, Employee obj) {
        Employee objFromDB = session.byId(Employee.class).load(id);
        objFromDB.setLogin(obj.getLogin());
        objFromDB.setPassword(obj.getPassword());
        objFromDB.setFio(obj.getFio());
        objFromDB.setDepartment(obj.getDepartment());
        objFromDB.setCity(obj.getCity());
        objFromDB.setSalary(obj.getSalary());
        objFromDB.setPhoneNumber(obj.getPhoneNumber());
        objFromDB.setEmail(obj.getEmail());
        session.flush();
    }
}
