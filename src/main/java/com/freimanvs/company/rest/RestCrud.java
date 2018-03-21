package com.freimanvs.company.rest;

import javax.validation.Valid;
import javax.ws.rs.core.Response;

public interface RestCrud<T> {
    Response getALL();
    Response add(@Valid T obj);
    Response getById(long id);
    Response delete(long id);
    Response update(long id, @Valid T obj);
}
