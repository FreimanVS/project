package com.freimanvs.company.dao;

import com.freimanvs.company.entities.Position;
import com.freimanvs.company.entities.Role;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class PositionDAO implements DAO<Position> {

    private Session session;

    public PositionDAO() {
    }

    public PositionDAO(Session session) {
        this.session = session;
    }

    @Override
    public long add(Position obj) {
        session.save(obj);
        return obj.getId();
    }

    @Override
    public List<Position> getList() {
        Query<Position> query = session.createQuery("from Position", Position.class);
        List<Position> list = query.getResultList();
        list.forEach(Hibernate::initialize);
        return list;
    }

    @Override
    public Position getById(long id) {
        return session.get(Position.class, id);
    }

    @Override
    public void deleteById(long id) {
        Position objFromDB = session.byId(Position.class).load(id);
        Hibernate.initialize(objFromDB);
        session.delete(objFromDB);
    }

    @Override
    public void updateById(long id, Position obj) {
        Position objFromDB = session.byId(Position.class).load(id);
        objFromDB.setName(obj.getName());
        objFromDB.setEmpls(obj.getEmpls());
        session.flush();
    }
}
