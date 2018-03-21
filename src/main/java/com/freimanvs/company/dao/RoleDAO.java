package com.freimanvs.company.dao;

import com.freimanvs.company.entities.Role;
import org.hibernate.Hibernate;
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
        List<Role> list = query.getResultList();

        if (list != null && !list.isEmpty())
            list.forEach(RoleDAO::initialize);

        return list;
    }

    @Override
    public Role getById(long id) {
        Role role = session.get(Role.class, id);
        initialize(role);
        return role;
    }

    @Override
    public void deleteById(long id) {
        Role objFromDB = session.byId(Role.class).load(id);
        initialize(objFromDB);
        session.delete(objFromDB);
    }

    @Override
    public void updateById(long id, Role obj) {
        Role objFromDB = session.byId(Role.class).load(id);
        initialize(objFromDB);
        objFromDB.setName(obj.getName());
        objFromDB.setEmpls(obj.getEmpls());
        session.flush();
    }

    public static void initialize(Role r) {
        Hibernate.initialize(r);
        r.getEmpls().forEach(Hibernate::initialize);
    }
}
