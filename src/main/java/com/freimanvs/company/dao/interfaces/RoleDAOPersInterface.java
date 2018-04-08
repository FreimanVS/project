package com.freimanvs.company.dao.interfaces;

import com.freimanvs.company.dao.DAO;
import com.freimanvs.company.entities.Role;

import javax.ejb.Remote;

@Remote
public interface RoleDAOPersInterface extends DAO<Role> {
}
