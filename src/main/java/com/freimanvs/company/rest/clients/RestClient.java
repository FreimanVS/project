package com.freimanvs.company.rest.clients;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestClient {

    private static final String host = "localhost";
    private static final String port = "8080";

    public static void main(String[] args) throws Exception {
        ClientBuilder clientBuilder = ClientBuilder.newBuilder();
        Client client = clientBuilder.build();
        WebTarget target =
                client.target("http://" + host + ":" + port + "/company/api/v1/employees");

        Response response = target
                .request(MediaType.APPLICATION_JSON).get();
        System.out.println(response.readEntity(String.class));
    }
}
