package com.freimanvs.company.rest.clients;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Future;

public class RestClient {

    private static final String host = "localhost";
    private static final String port = "8080";

    public static void main(String[] args) throws Exception {
        ClientBuilder clientBuilder = ClientBuilder.newBuilder();
//        clientBuilder.register(ClientLoggingFilter.class);
        Client client = clientBuilder.build();
        WebTarget target =
                client.target("http://" + host + ":" + port + "/company/api/v1/employees")
//                .resolveTemplatesFromEncoded(formparams)
//                .resolveTemplate("value", 144)
                ;

        Response response = target
                .request(MediaType.APPLICATION_JSON)
//                .request(MediaType.TEXT_HTML)
//                .post(Entity.entity(new User(), "application/json");
//                .header("myHeader", "The header value")
//                .cookie("myCookie", "The cookie value")
                .get();
        System.out.println(response.readEntity(String.class));

//        Future<String> result = target.request().async().get(new InvocationCallback<String>() {
//            @Override
//            public void completed(String result) {
//                // Do something with the customer object
//                System.out.println(result);
//            }
//            @Override
//            public void failed(Throwable throwable) {
//                // handle the error
//                System.out.println(throwable.getLocalizedMessage());
//            }
//        });

//        CompletionStage<String> csf = target.request().rx().get(String.class);
//        csf.thenAccept(System.out::println);
//
//        Invocation i1 = target.request().buildGet();
//        System.out.println(response.readEntity(String.class));
//        System.out.println(result.get());
//        System.out.println(i1.invoke(String.class));
    }
}
