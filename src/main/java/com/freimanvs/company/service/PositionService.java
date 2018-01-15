package com.freimanvs.company.service;

import com.freimanvs.company.dao.PositionDAO;
import com.freimanvs.company.entities.Position;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class PositionService implements Service<Position> {
    private Session session;

    public PositionService(Session session) {
        this.session = session;
    }

    @Override
    public long add(Position obj) {
        Transaction transaction = session.beginTransaction();
        try {
            long id = new PositionDAO(session).add(obj);
            transaction.commit();
            return id;
        } catch (Exception e) {
            transaction.rollback();
            return -1;
        }
    }

    @Override
    public List<Position> getList() {
        Transaction transaction = session.beginTransaction();
        try {
            List<Position> list = new PositionDAO(session).getList();
            transaction.commit();
            return list;
        } catch (Exception e) {
            transaction.rollback();
            return null;
        }
    }

    @Override
    public Position getById(long id) {
        Transaction transaction = session.beginTransaction();
        try {
            Position pos = new PositionDAO(session).getById(id);
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
            new PositionDAO(session).deleteById(id);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    @Override
    public void updateById(long id, Position obj) {
        Transaction transaction = session.beginTransaction();
        try {
            new PositionDAO(session).updateById(id, obj);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }
}
