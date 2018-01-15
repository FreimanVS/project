package com.freimanvs.company.dao;

import com.freimanvs.company.entities.Role;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class RoleDAO implements DAO<Role> {

    private Session session;

    public RoleDAO() {
    }

    public RoleDAO(Session session) {
        this.session = session;
    }

    @Override
    public long add(Role obj) {
        session.save(obj);
        return obj.getId();
    }

    @Override
    public List<Role> getList() {
        Query<Role> query = session.createQuery("from Role", Role.class);
        return query.getResultList();
    }

    @Override
    public Role getById(long id) {
        return session.get(Role.class, id);
    }

    @Override
    public void deleteById(long id) {
        Role objFromDB = session.byId(Role.class).load(id);
        session.delete(objFromDB);
    }

    @Override
    public void updateById(long id, Role obj) {
        Role objFromDB = session.byId(Role.class).load(id);
        objFromDB.setName(obj.getName());
        objFromDB.setEmpls(obj.getEmpls());
        session.flush();
    }
}
