package com.freimanvs.company.soapjaxws.cities;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Path("/v1/countries")
public class CitiesRestService {

    @Path("/{countryname}/cities")
    @GET
    @Produces(MediaType.APPLICATION_XML + ";charset=UTF-8")
    public Response get(@PathParam("countryname") String countryname) {
        try {
            URL url = new URL("http://www.webservicex.net/globalweather.asmx/GetCitiesByCountry?CountryName=" + countryname);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            StringBuilder sb = new StringBuilder();
            try (BufferedReader bReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                String line;
                while ((line = bReader.readLine()) != null) {
                    sb.append(line);
                }
            }

            return Response.status(200).entity(sb.toString()).build();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
