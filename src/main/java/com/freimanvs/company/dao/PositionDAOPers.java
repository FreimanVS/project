package com.freimanvs.company.dao;

import com.freimanvs.company.dao.interfaces.PositionDAOPersInterface;
import com.freimanvs.company.entities.Position;
import org.hibernate.Hibernate;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PositionDAOPers implements PositionDAOPersInterface {

    @PersistenceContext(unitName = "mysqlejb")
    EntityManager em;

    public PositionDAOPers() {
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public long add(Position obj) {
        em.persist(obj);
        em.flush();
        return obj.getId();
    }

    @Override
    public List<Position> getList() {
        List<Position> list = em.createQuery("SELECT c FROM Position c", Position.class).getResultList();

        if (list != null && !list.isEmpty())
            list.forEach(this::initialize);

        return list;
    }

    @Override
    public Position getById(long id) {
        Position pos = em.find(Position.class, id);
        initialize(pos);
        return pos;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void deleteById(long id) {
        Position objFromDB = getById(id);
        em.remove(objFromDB);
        em.flush();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void updateById(long id, Position obj) {
        Position objFromDB = getById(id);
        objFromDB.setName(obj.getName());
        objFromDB.setEmpls(obj.getEmpls());
        em.flush();
    }

    private void initialize(Position p) {
        if (p != null) {
            Hibernate.initialize(p);
            p.getEmpls().forEach(Hibernate::initialize);
        }
    }
}