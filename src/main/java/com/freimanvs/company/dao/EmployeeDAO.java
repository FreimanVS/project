package com.freimanvs.company.dao;

import com.freimanvs.company.entities.Employee;
import com.freimanvs.company.entities.Position;
import org.hibernate.Hibernate;
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
        List<Employee> list = query.getResultList();

        if (list != null && !list.isEmpty())
            list.forEach(EmployeeDAO::initialize);

        return list;
    }

    @Override
    public Employee getById(long id) {
        Employee emp = session.get(Employee.class, id);
        initialize(emp);
        return emp;
    }

    @Override
    public void deleteById(long id) {
        Employee objFromDB = session.byId(Employee.class).load(id);
        initialize(objFromDB);
        session.delete(objFromDB);
    }

    @Override
    public void updateById(long id, Employee obj) {
        Employee objFromDB = session.byId(Employee.class).load(id);
        initialize(objFromDB);
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
        session.flush();
    }

    public static void initialize(Employee e) {
        Hibernate.initialize(e);
        e.getPositions().forEach(Hibernate::initialize);
        e.getRoles().forEach(Hibernate::initialize);
    }
}
