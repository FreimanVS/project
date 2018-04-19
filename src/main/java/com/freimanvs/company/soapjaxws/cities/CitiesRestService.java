package com.freimanvs.company.soapjaxws.cities;

import com.freimanvs.company.soapjaxws.cities.beans.interfaces.Provider;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/countries")
public class CitiesRestService {

//    @EJB
    @Inject
    private Provider provider;

    @Path("/{countryname}/cities")
    @GET
    @Produces(MediaType.APPLICATION_XML + ";charset=UTF-8")
    public Response get(@PathParam("countryname") String countryname) {
        String urlString = "http://www.webservicex.net/globalweather.asmx/GetCitiesByCountry?CountryName=" + countryname;
        return Response.status(200).entity(provider.getResponse(urlString)).build();
    }
}
