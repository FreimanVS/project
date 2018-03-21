package com.freimanvs.company.rest.exception;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MyExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(final ConstraintViolationException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
            .entity(prepareMessage(exception))
            .type(MediaType.TEXT_HTML + ";charset=UTF-8")
            .build();
        }

    private String prepareMessage(ConstraintViolationException exception) {
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation<?> cv : exception.getConstraintViolations()) {
            sb.append(cv.getPropertyPath()).append(" : ").append(cv.getMessage()).append("<br/>");
        }
        return sb.toString();
    }
}
