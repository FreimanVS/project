package com.freimanvs.company.service.interfaces;

import javax.ejb.Remote;

@Remote
public interface AdminService {
    void assignRoles(String login, String[] roles);
}
