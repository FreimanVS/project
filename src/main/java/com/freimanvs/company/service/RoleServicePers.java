package com.freimanvs.company.service;

import com.freimanvs.company.dao.interfaces.RoleDAOPersInterface;
import com.freimanvs.company.entities.Role;
import com.freimanvs.company.service.interfaces.RoleServicePersInterface;

import javax.ejb.*;
import java.util.List;

@Stateless
public class RoleServicePers implements RoleServicePersInterface {

    @EJB
    RoleDAOPersInterface roleDAO;

    public RoleServicePers() {
    }

    @Override
    public long add(Role obj) {
        return roleDAO.add(obj);
    }

    @Override
    public List<Role> getList() {
        return roleDAO.getList();
    }

    @Override
    public Role getById(long id) {
        return roleDAO.getById(id);
    }

    @Override
    public void deleteById(long id) {
        roleDAO.deleteById(id);
    }

    @Override
    public void updateById(long id, Role obj) {
        roleDAO.updateById(id, obj);
    }
}
