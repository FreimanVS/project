package com.freimanvs.company.service.interfaces;

public interface AuthorizationService {
    boolean isAuthorized(String login, String password);
}
