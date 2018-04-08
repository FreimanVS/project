package com.freimanvs.company.service.interfaces;

import com.freimanvs.company.entities.Role;
import com.freimanvs.company.service.Service;

import javax.ejb.Remote;

@Remote
public interface RoleServicePersInterface extends Service<Role> {
}
