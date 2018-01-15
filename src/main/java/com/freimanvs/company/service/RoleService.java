package com.freimanvs.company.service;

import com.freimanvs.company.dao.RoleDAO;
import com.freimanvs.company.entities.Role;
import com.freimanvs.company.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class RoleService implements Service<Role> {
    private Session session;

    public RoleService(Session session) {
        this.session = session;
    }

    @Override
    public long add(Role obj) {
        Transaction transaction = session.beginTransaction();
        try {
            long id = new RoleDAO(session).add(obj);
            transaction.commit();
            return id;
        } catch (Exception e) {
            transaction.rollback();
            return -1;
        }
    }

    @Override
    public List<Role> getList() {
        Transaction transaction = session.beginTransaction();
        try {
            List<Role> list = new RoleDAO(session).getList();
            transaction.commit();
            return list;
        } catch (Exception e) {
            transaction.rollback();
            return null;
        }
    }

    @Override
    public Role getById(long id) {
        Transaction transaction = session.beginTransaction();
        try {
            Role role = new RoleDAO(session).getById(id);
            transaction.commit();
            return role;
        } catch (Exception e) {
            transaction.rollback();
            return null;
        }
    }

    @Override
    public void deleteById(long id) {
        Transaction transaction = session.beginTransaction();
        try {
            new RoleDAO(session).deleteById(id);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    @Override
    public void updateById(long id, Role obj) {
        Transaction transaction = session.beginTransaction();
        try {
            new RoleDAO(session).updateById(id, obj);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }
}
