package com.freimanvs.company.jsp;

import org.junit.Ignore;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.freimanvs.company.Constants.BASE_URI;
import static com.freimanvs.company.Constants.PORT;
import static com.freimanvs.company.Constants.CONTEXT_PATH;

import static org.junit.Assert.*;

//@Ignore
public class ChatServletTest {

    @Test
    public void connect() {
        ClientBuilder clientBuilder = ClientBuilder.newBuilder();
        Client client = clientBuilder.build();
        WebTarget target = client.target(BASE_URI + ":" + PORT + CONTEXT_PATH + "/chat");

        Response response = target.request(MediaType.TEXT_HTML).get();
        assertEquals(200, response.getStatus());
    }
}