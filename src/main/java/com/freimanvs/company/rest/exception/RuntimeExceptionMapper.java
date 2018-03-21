package com.freimanvs.company.rest.exception;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {
        Response.Status status;
        if (exception instanceof NotFoundException)
            status = Response.Status.NOT_FOUND;
        else if (exception instanceof BadRequestException)
            status = Response.Status.BAD_REQUEST;
        else if (exception instanceof ForbiddenException)
            status = Response.Status.FORBIDDEN;
        else if (exception instanceof NotAllowedException)
            status = Response.Status.METHOD_NOT_ALLOWED;
        else if (exception instanceof NotAcceptableException)
            status = Response.Status.NOT_ACCEPTABLE;
        else
            status = Response.Status.SEE_OTHER;

        return Response.status(status)
                .entity(exception.getLocalizedMessage())
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build();
    }
}
