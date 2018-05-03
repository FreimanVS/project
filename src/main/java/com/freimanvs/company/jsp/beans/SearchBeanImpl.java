package com.freimanvs.company.jsp.beans;

import com.freimanvs.company.entities.Employee;
import com.freimanvs.company.jsp.beans.interfaces.SearchBean;
import org.hibernate.Hibernate;

import javax.ejb.Stateful;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateful
@TransactionManagement(TransactionManagementType.BEAN)
public class SearchBeanImpl implements SearchBean {

    @PersistenceContext(unitName = "mysqlejb")
    private EntityManager em;

    public List<Employee> getBy(String login, String fio, String position, String city,
                                 int ageFrom, int ageTo) {

        TypedQuery<Employee> query = em.createQuery("select DISTINCT e from Employee e join e.positions p"
                +" WHERE e.user.login LIKE :login"
                + " AND e.fio LIKE :fio"
                + " AND p.name LIKE :positions"
                + " AND e.city LIKE :city"
                + " AND e.age >= :ageFrom"
                + " AND e.age <= :ageTo", Employee.class)
                .setParameter("login", "%"+login+"%")
                .setParameter("fio", "%"+fio+"%")
                .setParameter("positions", "%"+position+"%")
                .setParameter("city", "%"+city+"%")
                .setParameter("ageFrom", ageFrom)
                .setParameter("ageTo", ageTo);

        List<Employee> list = query.getResultList();

        if (list != null && !list.isEmpty()) {
            list.forEach(this::initialize);
        }

        return list;
    }

    private void initialize(Employee e) {
        Hibernate.initialize(e);
        e.getPositions().forEach(Hibernate::initialize);
        e.getRoles().forEach(Hibernate::initialize);
    }
}
