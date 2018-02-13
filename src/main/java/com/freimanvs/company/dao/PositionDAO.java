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

        if (list != null && !list.isEmpty())
            list.forEach(PositionDAO::initialize);

        return list;
    }

    @Override
    public Position getById(long id) {
        Position pos = session.get(Position.class, id);
        initialize(pos);
        return pos;
    }

    @Override
    public void deleteById(long id) {
        Position objFromDB = session.byId(Position.class).load(id);
        initialize(objFromDB);
        session.delete(objFromDB);
    }

    @Override
    public void updateById(long id, Position obj) {
        Position objFromDB = session.byId(Position.class).load(id);
        objFromDB.setName(obj.getName());
        objFromDB.setEmpls(obj.getEmpls());
        session.flush();
    }

    public static void initialize(Position p) {
        Hibernate.initialize(p);
        p.getEmpls().forEach(Hibernate::initialize);
    }
}
