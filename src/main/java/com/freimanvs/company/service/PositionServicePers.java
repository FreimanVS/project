package com.freimanvs.company.service;

import com.freimanvs.company.dao.interfaces.PositionDAOPersInterface;
import com.freimanvs.company.entities.Position;
import com.freimanvs.company.interceptors.bindings.Measurable;
import com.freimanvs.company.service.interfaces.PositionServicePersInterface;

import javax.ejb.*;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

//@Stateless
@Measurable
@Dependent
public class PositionServicePers implements PositionServicePersInterface {

    @EJB
    private PositionDAOPersInterface positionDAO;

    public PositionServicePers() {
    }

    @Override
    public long add(Position obj) {
        return positionDAO.add(obj);
    }

    @Override
    public List<Position> getList() {
        return positionDAO.getList();
    }

    @Override
    public Position getById(long id) {
        return positionDAO.getById(id);
    }

    @Override
    public void deleteById(long id) {
        positionDAO.deleteById(id);
    }

    @Override
    public void updateById(long id, Position obj) {
        positionDAO.updateById(id, obj);
    }
}
