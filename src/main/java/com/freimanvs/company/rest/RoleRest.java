package com.freimanvs.company.rest;

import com.freimanvs.company.entities.Role;
import com.freimanvs.company.service.RoleServicePers;
import com.freimanvs.company.service.interfaces.RoleServicePersInterface;
import io.swagger.annotations.*;

import javax.ejb.EJB;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/v1/roles")
@SwaggerDefinition(
        info = @Info(
                title = "Role Resource Swagger-generated API",
                description = "Role Resource Description example",
                version = "1.0.0",
                termsOfService = "share and care",
                contact = @Contact(
                        name = "name", email = "mail@mail.com",
                        url = "http://www.url.com"),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org")),
        tags = {
                @Tag(name = "Role Resource Swagger-generated API",
                        description = "Description Example")
        },
        basePath = "/company/api",
        schemes = {SwaggerDefinition.Scheme.HTTP},
        externalDocs = @ExternalDocs(
                value = "Developing a Swagger-enabled REST API using WebSphere Developer Tools",
                url = "https://tinyurl.com/swagger-wlp"))
@Api(tags = "Role Resource Swagger-generated API", produces = MediaType.APPLICATION_JSON)
public class RoleRest implements RestCrud<Role> {

//    @EJB
//    @Inject
    private RoleServicePersInterface roleService =
//        CDI.current().select(RoleServicePers.class).get()
        CDI.current().select(RoleServicePersInterface.class).get()
        ;

    @Context
    private UriInfo info;

    @ApiOperation(value = "Get all roles",
            notes = "Get all roles",
            produces = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
    })

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Override
    public Response getALL() {
        List<Role> roles =  roleService.getList();
        return Response.ok(roles).build();
    }



    @ApiOperation(value = "Create a role",
            notes = "Create a role",
            consumes = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @ApiResponses({
            @ApiResponse(code = 201, message = "A role has been created"),
            @ApiResponse(code = 400, message = "bad request")
    })

    @POST
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Override
    public Response add(Role obj) {
        long id = roleService.add(obj);
        return Response.created(info.getAbsolutePathBuilder().path("/" + id).build()).build();
    }



    @ApiOperation(value = "Get a role by id",
            notes = "Get a role by id",
            produces = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 404, message = "not found")
    })
    @ApiImplicitParam(name = "id", value = "id", dataType = "long", paramType = "path")

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Override
    public Response getById(@PathParam("id") long id) {
        Role role = roleService.getById(id);

        if (role == null)
            throw new NotFoundException();

        return Response.ok(role).build();
    }



    @ApiOperation(value = "Delete a role",
            notes = "Delete a role")
    @ApiResponses({
            @ApiResponse(code = 204, message = "The role has been deleted"),
            @ApiResponse(code = 404, message = "not found")
    })
    @ApiImplicitParam(name = "id", value = "id", dataType = "long", paramType = "path")

    @DELETE
    @Path("/{id}")
    @Override
    public Response delete(@PathParam("id") long id) {

        if (roleService.getById(id) == null)
            throw new NotFoundException();

        roleService.deleteById(id);
        return Response.noContent().build();
    }



    @ApiOperation(value = "Update a role",
            notes = "Update a role",
            consumes = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @ApiResponses({
            @ApiResponse(code = 201, message = "A role has been updated"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 404, message = "not found")
    })
    @ApiImplicitParam(name = "id", value = "id", dataType = "long", paramType = "path")

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Override
    public Response update(@PathParam("id") long id,
                           Role obj) {

        if (roleService.getById(id) == null)
            throw new NotFoundException();

        roleService.updateById(id, obj);
        return Response.created(info.getAbsolutePath()).build();
    }
}
