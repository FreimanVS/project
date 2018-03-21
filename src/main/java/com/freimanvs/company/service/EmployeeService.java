package com.freimanvs.company.service;

import com.freimanvs.company.dao.DAO;
import com.freimanvs.company.dao.EmployeeDAO;
import com.freimanvs.company.dao.PositionDAO;
import com.freimanvs.company.dao.RoleDAO;
import com.freimanvs.company.entities.Employee;
import com.freimanvs.company.entities.Position;
import com.freimanvs.company.entities.Role;
import com.freimanvs.company.security.Encode;
import com.freimanvs.company.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EmployeeService implements Service<Employee> {
    private Session session;

    private DAO<Employee> employeeDAO;

    public EmployeeService() {
        session = HibernateUtil.getSessionFactory().openSession();
        employeeDAO = new EmployeeDAO(session);
    }

    public EmployeeService(Session session) {
        this.session = session;
        employeeDAO = new EmployeeDAO(session);
    }

    @Override
    public long add(Employee obj) {
        Transaction transaction = session.beginTransaction();
        try {
            //encode the password
            obj.setPassword(Encode.sha(obj.getPassword()));

//            Role user = new RoleDAO(session).getById(1L);
//
//            //if admin
//            if (obj.getLogin().equals("admin")) {
//                Role admin = new RoleDAO(session).getById(2L);
//                obj.getRoles().add(user);
//                obj.getRoles().add(admin);
//                Position accounter = new PositionDAO(session).getById(3L);
//                Position director = new PositionDAO(session).getById(4L);
//                obj.getPositions().add(accounter);
//                obj.getPositions().add(director);
//            }
//
//            //others
//            else {
//                //add a worker position
//                Position worker = new PositionDAO(session).getById(1L);
//                obj.getPositions().add(worker);
//                obj.getRoles().add(user);
//            }

            long id = employeeDAO.add(obj);
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
            List<Employee> list = employeeDAO.getList();
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
            Employee pos = employeeDAO.getById(id);
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
            employeeDAO.deleteById(id);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    @Override
    public void updateById(long id, Employee obj) {
        Transaction transaction = session.beginTransaction();
        try {
            obj.setPassword(Encode.sha(obj.getPassword()));
            employeeDAO.updateById(id, obj);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }
}
