package com.freimanvs.company.rest;

import com.freimanvs.company.entities.Position;
import com.freimanvs.company.service.PositionServicePers;
import com.freimanvs.company.service.interfaces.PositionServicePersInterface;
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

@Path("/v1/positions")
@SwaggerDefinition(
        info = @Info(
                title = "Position Resource Swagger-generated API",
                description = "Position Resource Description example",
                version = "1.0.0",
                termsOfService = "share and care",
                contact = @Contact(
                        name = "name", email = "mail@mail.com",
                        url = "http://www.url.com"),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org")),
        tags = {
                @Tag(name = "Position Resource Swagger-generated API",
                        description = "Description Example")
        },
        basePath = "/company/api",
        schemes = {SwaggerDefinition.Scheme.HTTP},
        externalDocs = @ExternalDocs(
                value = "Developing a Swagger-enabled REST API using WebSphere Developer Tools",
                url = "https://tinyurl.com/swagger-wlp"))
@Api(tags = "Position Resource Swagger-generated API", produces = MediaType.APPLICATION_JSON)
public class PositionRest implements RestCrud<Position> {

//    @EJB
//    @Inject
    private PositionServicePersInterface positionService =
        CDI.current().select(PositionServicePers.class).get()
        ;

    @Context
    private UriInfo info;

    @ApiOperation(value = "Get all positions",
            notes = "Get all positions",
            produces = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
    })

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Override
    public Response getALL() {
        List<Position> positions =  positionService.getList();
        return Response.ok(positions).build();
    }



    @ApiOperation(value = "Create a position",
            notes = "Create a position",
            consumes = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @ApiResponses({
            @ApiResponse(code = 201, message = "A position has been created"),
            @ApiResponse(code = 400, message = "bad request")
    })

    @POST
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Override
    public Response add(Position obj) {
        long id = positionService.add(obj);
        return Response.created(info.getAbsolutePathBuilder().path("/" + id).build()).build();
    }



    @ApiOperation(value = "Get a position by id",
            notes = "Get a position by id",
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
        Position position = positionService.getById(id);

        if (position == null)
            throw new NotFoundException();

        return Response.ok(position).build();
    }



    @ApiOperation(value = "Delete a position",
            notes = "Delete a position")
    @ApiResponses({
            @ApiResponse(code = 204, message = "The position has been deleted"),
            @ApiResponse(code = 404, message = "not found")
    })
    @ApiImplicitParam(name = "id", value = "id", dataType = "long", paramType = "path")

    @DELETE
    @Path("/{id}")
    @Override
    public Response delete(@PathParam("id") long id) {

        if (positionService.getById(id) == null)
            throw new NotFoundException();

        positionService.deleteById(id);
        return Response.noContent().build();
    }



    @ApiOperation(value = "Update a position",
            notes = "Update a position",
            consumes = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @ApiResponses({
            @ApiResponse(code = 201, message = "A position has been updated"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 404, message = "not found")
    })
    @ApiImplicitParam(name = "id", value = "id", dataType = "long", paramType = "path")

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Override
    public Response update(@PathParam("id") long id,
                           Position obj) {

        if (positionService.getById(id) == null)
            throw new NotFoundException();

        positionService.updateById(id, obj);
        return Response.created(info.getAbsolutePath()).build();
    }
}
