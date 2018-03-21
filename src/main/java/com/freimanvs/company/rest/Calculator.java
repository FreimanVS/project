package com.freimanvs.company.rest;

import javax.ws.rs.core.Response;

public interface Calculator {
    Response calculate(int t,
                       double kr,
                       double st);
}
