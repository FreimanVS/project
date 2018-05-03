package com.freimanvs.company.interceptors.dao;

import com.freimanvs.company.interceptors.bindings.Logging;
import com.freimanvs.company.interceptors.dao.interfaces.PerformanceDAO;
import com.freimanvs.company.interceptors.models.Performance;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@Logging
public class PerformanceDAOImpl implements PerformanceDAO {

    @PersistenceContext(unitName = "mysqlejb")
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public long add(Performance obj) {
        Performance newObj = em.merge(obj);
        em.flush();
        return newObj.getId();
    }

    @Override
    public List<Performance> getList() {
        return em.createQuery("SELECT c FROM Performance c", Performance.class).getResultList();
    }

    @Override
    public Performance getById(long id) {
        return em.find(Performance.class, id);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void deleteById(long id) {
        em.remove(getById(id));
        em.flush();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void updateById(long id, Performance obj) {
        Performance objFromDB = getById(id);
        objFromDB.setName(obj.getName());
        objFromDB.setMs(obj.getMs());
        em.flush();
    }
}