package com.freimanvs.company.service;

import com.freimanvs.company.dao.DAO;
import com.freimanvs.company.dao.RoleDAO;
import com.freimanvs.company.dao.RoleDAOPers;
import com.freimanvs.company.entities.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class RoleServicePers implements Service<Role> {
    private EntityManager em;

    public RoleServicePers() {
    }

    public RoleServicePers(EntityManager em) {
        this.em = em;
    }

    @Override
    public long add(Role obj) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            long id = new RoleDAOPers(em).add(obj);
            transaction.commit();
            return id;
        } catch (Exception e) {
            transaction.rollback();
            return -1;
        }
    }

    @Override
    public List<Role> getList() {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            List<Role> list = new RoleDAOPers(em).getList();
            transaction.commit();
            return list;
        } catch (Exception e) {
            transaction.rollback();
            return null;
        }
    }

    @Override
    public Role getById(long id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            Role role = new RoleDAOPers(em).getById(id);
            transaction.commit();
            return role;
        } catch (Exception e) {
            transaction.rollback();
            return null;
        }
    }

    @Override
    public void deleteById(long id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            new RoleDAOPers(em).deleteById(id);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    @Override
    public void updateById(long id, Role obj) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            new RoleDAOPers(em).updateById(id, obj);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }
}
