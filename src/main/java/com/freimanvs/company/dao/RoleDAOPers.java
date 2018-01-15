package com.freimanvs.company.dao;

import com.freimanvs.company.entities.Role;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class RoleDAOPers implements DAO<Role> {
    private EntityManager em;

    public RoleDAOPers() {
    }

    public RoleDAOPers(EntityManager em) {
        this.em = em;
    }

    @Override
    public long add(Role obj) {
        em.persist(obj);
        return obj.getId();
    }

    @Override
    public List<Role> getList() {
        TypedQuery<Role> query = em.createQuery("from Role", Role.class);
        return query.getResultList();
    }

    @Override
    public Role getById(long id) {
        return em.find(Role.class, id);
    }

    @Override
    public void deleteById(long id) {
        Role objFromDB = getById(id);
        em.remove(objFromDB);
    }

    @Override
    public void updateById(long id, Role obj) {
        Role objFromDB = em.find(Role.class, id);
        objFromDB.setName(obj.getName());
        objFromDB.setEmpls(obj.getEmpls());
        em.flush();
    }
}
