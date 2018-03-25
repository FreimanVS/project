package com.freimanvs.company.soapjaxws.geolocation;

import com.freimanvs.company.soapjaxws.geolocation.fromwsdl.GeoIP;
import com.freimanvs.company.soapjaxws.geolocation.fromwsdl.GeoIPService;
import com.freimanvs.company.soapjaxws.geolocation.fromwsdl.GeoIPServiceSoap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/geoIP")
public class GeoIPRestService {

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response get() {

        GeoIPService geoIPService = new GeoIPService();
        GeoIPServiceSoap geoIPServiceSoap = geoIPService.getGeoIPServiceSoap();
        GeoIP geoIPContext = geoIPServiceSoap.getGeoIPContext();

        String result = String.format("{\"country\": \"%s\", \"ip\": \"%s\"}",
                geoIPContext.getCountryName(), geoIPContext.getIP());

        return Response.status(200).entity(result).build();
    }
}
