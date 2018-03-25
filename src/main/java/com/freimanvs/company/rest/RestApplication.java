package com.freimanvs.company.rest;

import com.freimanvs.company.rest.exception.MyExceptionMapper;
import com.freimanvs.company.rest.exception.RuntimeExceptionMapper;
import com.freimanvs.company.soapjaxws.cities.CitiesRestService;
import com.freimanvs.company.soapjaxws.geolocation.GeoIPRestService;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class RestApplication extends Application {

    public RestApplication() {
    }

    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        addExceptionMapperClasses(resources);

        // enable Swagger
        addSwaggerClasses(resources);

        return resources;
    }

    private void addSwaggerClasses(Set<Class<?>> resources) {
        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
    }

    private void addExceptionMapperClasses(Set<Class<?>> resources) {
        resources.add(MyExceptionMapper.class);
        resources.add(RuntimeExceptionMapper.class);
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(DifferentialPaymentService.class);
        resources.add(AnnuityPaymentService.class);
        resources.add(EmployeeRest.class);
        resources.add(PositionRest.class);
        resources.add(RoleRest.class);
        resources.add(GeoIPRestService.class);
        resources.add(CitiesRestService.class);
    }
}