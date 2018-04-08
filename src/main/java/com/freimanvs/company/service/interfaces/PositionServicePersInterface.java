package com.freimanvs.company.service.interfaces;

import com.freimanvs.company.entities.Position;
import com.freimanvs.company.service.Service;

import javax.ejb.Remote;

@Remote
public interface PositionServicePersInterface extends Service<Position> {
}
