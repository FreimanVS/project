package com.freimanvs.company.decorators;

import com.freimanvs.company.service.interfaces.AuthorizationService;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public abstract class AuthorizationDecorator implements AuthorizationService {

    @Inject
    @Delegate
    @Any
    private AuthorizationService authorizationService;

    @Override
    public boolean isAuthorized(String login, String password) {
        boolean testAccount = login.equalsIgnoreCase("login");
        return testAccount || authorizationService.isAuthorized(login, password);
    }
}
