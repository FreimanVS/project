package com.freimanvs.company.rest;

import com.freimanvs.company.entities.Employee;
import com.freimanvs.company.interceptors.bindings.Logging;
import com.freimanvs.company.rest.interceptors.NotFoundInterceptor;
import com.freimanvs.company.service.EmployeeServicePers;
import com.freimanvs.company.service.interfaces.EmployeeServicePersInterface;
import io.swagger.annotations.*;

import javax.ejb.EJB;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/v1/employees")
@Logging
@SwaggerDefinition(
        info = @Info(
                title = "Employee Resource Swagger-generated API",
                description = "Employee Resource Description example",
                version = "1.0.0",
                termsOfService = "share and care",
                contact = @Contact(
                        name = "name", email = "mail@mail.com",
                        url = "http://www.url.com"),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org")),
        tags = {
                @Tag(name = "Employee Resource Swagger-generated API",
                        description = "Description Example")
        },
        basePath = "/company/api",
        schemes = {SwaggerDefinition.Scheme.HTTP},
        externalDocs = @ExternalDocs(
                value = "Developing a Swagger-enabled REST API using WebSphere Developer Tools",
                url = "https://tinyurl.com/swagger-wlp"))
@Api(tags = "Employee Resource Swagger-generated API", produces = MediaType.APPLICATION_JSON)
public class EmployeeRest implements RestCrud<Employee> {

//    @EJB
//    @Inject
//    @EmployeeService(value = EmployeeServiceEnum.PERS)
    private EmployeeServicePersInterface employeeService
        = CDI.current().select(EmployeeServicePers.class).get()
        ;

    @Context
    private UriInfo info;

    @ApiOperation(value = "Get all employees",
            notes = "Get all employees",
            produces = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
    })

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Override
    public Response getALL() {
        List<Employee> employees =  employeeService.getList();
        return Response.ok(employees).build();
    }



    @ApiOperation(value = "Create an employee",
            notes = "Create employee",
            consumes = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @ApiResponses({
            @ApiResponse(code = 201, message = "An employee has been created"),
            @ApiResponse(code = 400, message = "bad request")
    })

    @POST
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Override
    public Response add(Employee obj) {
        long id = employeeService.add(obj);
        return Response.created(info.getAbsolutePathBuilder().path("/" + id).build()).build();
    }



    @ApiOperation(value = "Get an employee by id",
            notes = "Get an employee by id",
            produces = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 404, message = "not found")
    })
    @ApiImplicitParam(name = "id", value = "id", dataType = "long", paramType = "path")

    @Interceptors({NotFoundInterceptor.class})
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Override
    public Response getById(@PathParam("id") long id) {
        Employee employee = employeeService.getById(id);

        return Response.ok(employee).build();
    }



    @ApiOperation(value = "Delete an employee",
            notes = "Delete an employee")
    @ApiResponses({
            @ApiResponse(code = 204, message = "The employee has been deleted"),
            @ApiResponse(code = 404, message = "not found")
    })
    @ApiImplicitParam(name = "id", value = "id", dataType = "long", paramType = "path")

    @Interceptors({NotFoundInterceptor.class})
    @DELETE
    @Path("/{id}")
    @Override
    public Response delete(@PathParam("id") long id) {

        employeeService.deleteById(id);
        return Response.noContent().build();
    }



    @ApiOperation(value = "Update an employee",
            notes = "Update an employee",
            consumes = MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @ApiResponses({
            @ApiResponse(code = 201, message = "An employee has been updated"),
            @ApiResponse(code = 400, message = "bad request"),
            @ApiResponse(code = 404, message = "not found")
    })
    @ApiImplicitParam(name = "id", value = "id", dataType = "long", paramType = "path")

    @Interceptors({NotFoundInterceptor.class})
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Override
    public Response update(@PathParam("id") long id,
                           Employee obj) {

        employeeService.updateById(id, obj);
        return Response.created(info.getAbsolutePath()).build();
    }

}
