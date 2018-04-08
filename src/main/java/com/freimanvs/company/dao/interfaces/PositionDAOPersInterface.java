package com.freimanvs.company.dao.interfaces;

import com.freimanvs.company.dao.DAO;
import com.freimanvs.company.entities.Position;

import javax.ejb.Remote;

@Remote
public interface PositionDAOPersInterface extends DAO<Position> {
}
