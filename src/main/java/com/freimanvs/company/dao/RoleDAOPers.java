package com.freimanvs.company.dao;

import com.freimanvs.company.dao.interfaces.RoleDAOPersInterface;
import com.freimanvs.company.entities.Role;
import org.hibernate.Hibernate;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class RoleDAOPers implements RoleDAOPersInterface {

    @PersistenceContext(unitName = "mysqlejb")
    private EntityManager em;

    public RoleDAOPers() {
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public long add(Role obj) {
        em.persist(obj);
        em.flush();
        return obj.getId();
    }

    @Override
    public List<Role> getList() {
        TypedQuery<Role> query = em.createQuery("SELECT c FROM Role c", Role.class);
        List<Role> list = query.getResultList();

        if (list != null && !list.isEmpty())
            list.forEach(this::initialize);

        return list;
    }

    @Override
    public Role getById(long id) {
        Role role = em.find(Role.class, id);
        initialize(role);
        return role;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void deleteById(long id) {
        Role objFromDB = getById(id);
        em.remove(objFromDB);
        em.flush();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void updateById(long id, Role obj) {
        Role objFromDB = getById(id);
        objFromDB.setName(obj.getName());
        objFromDB.setEmpls(obj.getEmpls());
        em.flush();
    }

    private void initialize(Role r) {
        if (r != null) {
            Hibernate.initialize(r);
            r.getEmpls().forEach(Hibernate::initialize);
        }
    }
}
