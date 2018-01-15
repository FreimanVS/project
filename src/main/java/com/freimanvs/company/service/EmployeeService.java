package com.freimanvs.company.service;

import com.freimanvs.company.dao.EmployeeDAO;
import com.freimanvs.company.dao.PositionDAO;
import com.freimanvs.company.dao.RoleDAO;
import com.freimanvs.company.entities.Employee;
import com.freimanvs.company.entities.Position;
import com.freimanvs.company.entities.Role;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class EmployeeService implements Service<Employee> {
    private Session session;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public EmployeeService(Session session) {
        this.session = session;
    }

    @Override
    public long add(Employee obj) {
        Transaction transaction = session.beginTransaction();
        try {
            obj.setPassword(bCryptPasswordEncoder.encode(obj.getPassword()));
            Role user = new RoleDAO(session).getById(1L);
            obj.getRoles().add(user);

            Position worker = new PositionDAO(session).getById(1L);
            obj.getPositions().add(worker);

            long id = new EmployeeDAO(session).add(obj);
            transaction.commit();
            return id;
        } catch (Exception e) {
            transaction.rollback();
            return -1;
        }
    }

    @Override
    public List<Employee> getList() {
        Transaction transaction = session.beginTransaction();
        try {
            List<Employee> list = new EmployeeDAO(session).getList();
            transaction.commit();
            return list;
        } catch (Exception e) {
            transaction.rollback();
            return null;
        }
    }

    @Override
    public Employee getById(long id) {
        Transaction transaction = session.beginTransaction();
        try {
            Employee pos = new EmployeeDAO(session).getById(id);
            transaction.commit();
            return pos;
        } catch (Exception e) {
            transaction.rollback();
            return null;
        }
    }

    @Override
    public void deleteById(long id) {
        Transaction transaction = session.beginTransaction();
        try {
            new EmployeeDAO(session).deleteById(id);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    @Override
    public void updateById(long id, Employee obj) {
        Transaction transaction = session.beginTransaction();
        try {
            new EmployeeDAO(session).updateById(id, obj);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }
}
