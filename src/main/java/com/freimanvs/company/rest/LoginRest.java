package com.freimanvs.company.rest;

import com.freimanvs.company.entities.User;
import com.freimanvs.company.interceptors.bindings.Logging;
import com.freimanvs.company.rest.interceptors.NoAuthInterceptor;
import com.freimanvs.company.service.AuthorizationServiceImpl;
import com.freimanvs.company.service.interfaces.AuthorizationService;
import io.swagger.annotations.*;

import javax.ejb.SessionContext;
import javax.enterprise.inject.spi.CDI;
import javax.interceptor.Interceptors;
import javax.security.auth.login.LoginException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.io.IOException;
import java.security.Principal;

@Path("/v1/auth")
@Logging
@SwaggerDefinition(
        info = @Info(
                title = "Auth Resource Swagger-generated API",
                description = "Auth Resource Description example",
                version = "1.0.0",
                termsOfService = "share and care",
                contact = @Contact(
                        name = "name", email = "mail@mail.com",
                        url = "http://www.url.com"),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org")),
        tags = {
                @Tag(name = "Auth Resource Swagger-generated API",
                        description = "Description Example")
        },
        basePath = "/company/api",
        schemes = {SwaggerDefinition.Scheme.HTTP},
        externalDocs = @ExternalDocs(
                value = "Developing a Swagger-enabled REST API using WebSphere Developer Tools",
                url = "https://tinyurl.com/swagger-wlp"))
@Api(tags = "Auth Resource Swagger-generated API", produces = MediaType.APPLICATION_JSON)
public class LoginRest {

//    private AuthorizationService authorizationService =
//            CDI.current().select(AuthorizationServiceImpl.class).get();

    @Context
    private HttpServletRequest request;

    @Context
    private HttpServletResponse response;

    @Context
    private SecurityContext securityContext;

    @ApiOperation(value = "login",
            notes = "login",
            consumes = MediaType.APPLICATION_JSON)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
    })

    @Interceptors({NoAuthInterceptor.class})
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(User loginInfo) {

        try {
            request.login(loginInfo.getLogin(), loginInfo.getPassword());
        } catch (ServletException e) {
            e.printStackTrace();
        }

        return Response.ok(securityContext.getUserPrincipal() == null
                ? "no" : securityContext.getUserPrincipal().getName()).build();
    }

    @GET
    @Path("/logout")
    public Response logout() throws ServletException {

        request.logout();
        return Response.ok().build();
    }

    @GET
    @Path("/principal")
    @Produces(MediaType.TEXT_PLAIN)
    public Response principal() {
        Principal principal = securityContext.getUserPrincipal();
        return Response.ok(principal != null ? principal.getName() : "not logged in").build();
    }
}
